package com.gmail.maystruks08.filmviewer.core.ext

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}


