package com.gmail.maystruks08.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.os.Environment
import com.gmail.maystruks08.data.remote.MovieApi
import com.gmail.maystruks08.domain.repository.ImageLoader
import okhttp3.ResponseBody
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject


class ImageLoaderImpl @Inject constructor(private val movieApi: MovieApi) : ImageLoader {

    companion object {

        private val cachePackageName = Environment.getExternalStorageDirectory().path + "/MovieImages/"

    }

    override suspend fun load(id: String, url: String): ByteArray? {
        val cachedImage = getFileFromCache(id)
        return if (cachedImage != null) {
            cachedImage
        } else {
            val response = movieApi.downloadFile(url)
            val path = response.body()?.let { storeFileToCache(id, it) }
            if (path == false) null
            else getFileFromCache(id)
        }
    }


    private fun getFileFromCache(path: String?): ByteArray? {
        return try {
            File("$cachePackageName/$path").readBytes()
        } catch (e: Exception) {
            Timber.e("Read file error ${e.message}")
            null
        }
    }

    private fun storeFileToCache(id: String, body: ResponseBody): Boolean {
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val data = input?.readBytes() ?: return false

            val options = BitmapFactory.Options().also { it.inMutable = true }
            val bmp: Bitmap = BitmapFactory.decodeByteArray(data, 0, data.size, options)
            Canvas(bmp)
            val bytes = ByteArrayOutputStream()
            bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes)

            val directoryImages = File(cachePackageName)
            if (!directoryImages.isDirectory || !directoryImages.exists()) {
                val mkdirsResult = directoryImages.mkdirs()
                if (!mkdirsResult) return false
            }
            val imageFile = File(directoryImages, id).apply { createNewFile() }
            FileOutputStream(imageFile).use {
                it.write(bytes.toByteArray())
                it.close()
            }
            return true
        } catch (e: Exception) {
            Timber.e("Save file error ${e.message}")
        } finally {
            input?.close()
        }
        return false
    }

}