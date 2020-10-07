package com.gmail.maystruks08.filmviewer.ui.movielist

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.gmail.maystruks08.domain.repository.FileHelper
import com.gmail.maystruks08.filmviewer.R
import com.gmail.maystruks08.filmviewer.core.base.BaseDiffUtil
import com.gmail.maystruks08.filmviewer.core.ext.decodeBitmap
import com.gmail.maystruks08.filmviewer.ui.viewmodels.MovieView
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MovieAdapter(
    private val fileHelper: FileHelper,
    private val viewLifecycleOwner: LifecycleOwner,
    private val diffUtilCallback: BaseDiffUtil,
    private val clickListener: (movieId: Int) -> Unit,
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var viewList: MutableList<MovieView> by Delegates.observable(mutableListOf()) { _, oldValue, newValue ->
        diffUtilCallback.calculateDiff(oldValue, newValue).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(viewList[position])
    }

    override fun getItemCount(): Int = viewList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                if (isAdapterPositionCorrect()) clickListener(viewList[adapterPosition].id)
            }
        }

        fun bindHolder(item: MovieView) {
            itemView.apply {
                tvMovieName.text = item.name
                tvMovieDate.text = item.date
            }
            viewLifecycleOwner.lifecycle.coroutineScope.launch {
                val imageLoader: Deferred<Bitmap?> = async(Dispatchers.IO) {
                    return@async item.imageLink?.let { fileHelper.load(item.id.toString(), it)?.decodeBitmap() }
                }
                val image = imageLoader.await()
                itemView.ivMoviePicture.setImageBitmap(image)
            }
        }

        private fun isAdapterPositionCorrect(): Boolean = adapterPosition in 0..viewList.lastIndex
    }
}