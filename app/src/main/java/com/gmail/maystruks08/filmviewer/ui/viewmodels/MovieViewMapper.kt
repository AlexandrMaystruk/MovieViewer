package com.gmail.maystruks08.filmviewer.ui.viewmodels

import com.gmail.maystruks08.domain.entities.Movie
import com.gmail.maystruks08.domain.util.toDateTimeFormat

fun Movie.toMovieView(): MovieView {
    return MovieView(id, name, image, date.toDateTimeFormat())
}

fun Movie.toMovieFullInfoView(): MovieFullInfoView {
    return MovieFullInfoView(id, name, image, description, date.toDateTimeFormat())
}