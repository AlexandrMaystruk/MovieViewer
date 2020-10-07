package com.gmail.maystruks08.filmviewer.core.di.movielist.description

import com.gmail.maystruks08.filmviewer.ui.description.MovieDescriptionFragment
import dagger.Subcomponent

@Subcomponent(modules = [MovieDescriptionModule::class])
@MovieListDescriptionScope
interface MovieDescriptionComponent {

    fun inject(fragment: MovieDescriptionFragment)

}