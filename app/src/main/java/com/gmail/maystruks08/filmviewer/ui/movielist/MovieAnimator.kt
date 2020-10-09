package com.gmail.maystruks08.filmviewer.ui.movielist

import android.view.ViewTreeObserver
import javax.inject.Inject

class MovieAnimator @Inject constructor() {

    internal fun setUpReturnTransition(fragment: MovieListFragment) {
        with(fragment) {
            postponeEnterTransition()
            view?.viewTreeObserver?.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    startPostponedEnterTransition()
                    view?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
                }
            })
        }
    }
}