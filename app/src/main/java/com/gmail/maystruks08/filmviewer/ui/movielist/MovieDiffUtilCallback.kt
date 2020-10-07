package com.gmail.maystruks08.filmviewer.ui.movielist

import androidx.recyclerview.widget.DiffUtil
import com.gmail.maystruks08.filmviewer.core.base.BaseDiffUtil
import com.gmail.maystruks08.filmviewer.ui.viewmodels.MovieView

class MovieDiffUtilCallback : BaseDiffUtil() {

    private var oldList: List<MovieView> = emptyList()
    private var newList: List<MovieView> = emptyList()

    @Suppress("UNCHECKED_CAST")
    override fun calculateDiff(oldList: List<*>, newList: List<*>): DiffUtil.DiffResult {
        this.oldList = oldList as List<MovieView>
        this.newList = newList as List<MovieView>
        return DiffUtil.calculateDiff(this)
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]
        return oldProduct.id == newProduct.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]
        return oldProduct.imageLink == newProduct.imageLink && oldProduct.date == newProduct.date
    }
}