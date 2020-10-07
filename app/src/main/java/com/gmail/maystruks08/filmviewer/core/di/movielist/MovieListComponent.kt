package com.gmail.maystruks08.filmviewer.core.di.movielist

import com.gmail.maystruks08.filmviewer.ui.movielist.MovieListFragment
import dagger.Subcomponent

@Subcomponent(modules = [MovieListModule::class])
@MovieListScope

interface MovieListComponent {

    fun inject(fragment: MovieListFragment)

}