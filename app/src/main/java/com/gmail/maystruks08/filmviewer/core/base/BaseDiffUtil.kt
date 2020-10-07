package com.gmail.maystruks08.filmviewer.core.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtil: DiffUtil.Callback() {

    abstract fun calculateDiff(oldList: List<*>, newList: List<*>): DiffUtil.DiffResult
}