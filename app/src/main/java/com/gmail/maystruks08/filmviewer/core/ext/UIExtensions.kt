package com.gmail.maystruks08.filmviewer.core.ext

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.View
import timber.log.Timber
import java.io.ByteArrayOutputStream

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}


fun ByteArray.decodeBitmap(): Bitmap? {
    return try {
        val options = BitmapFactory.Options().also { it.inMutable = true }
        val bitmap = BitmapFactory.decodeByteArray(this, 0, this.size, options)
        Canvas(bitmap)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream)
        bitmap
    } catch (e: Exception) {
        Timber.e(e)
        null
    }
}


