package com.gmail.maystruks08.filmviewer.ui.viewmodels

import android.graphics.Bitmap
import com.gmail.maystruks08.domain.entities.Movie
import com.gmail.maystruks08.domain.util.toDateTimeShortFormat

fun Movie.toMovieView(): MovieView {
    return MovieView(id, name, image, date.toDateTimeShortFormat())
}

fun Movie.toMovieFullInfoView(bitmap: Bitmap?): MovieFullInfoView {
    return MovieFullInfoView(id, name, bitmap, description)
}