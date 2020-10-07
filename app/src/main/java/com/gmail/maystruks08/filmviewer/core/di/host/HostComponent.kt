package com.gmail.maystruks08.filmviewer.core.di.host

import com.gmail.maystruks08.filmviewer.HostActivity
import com.gmail.maystruks08.filmviewer.core.di.movielist.MovieListComponent
import com.gmail.maystruks08.filmviewer.core.di.movielist.description.MovieDescriptionComponent
import dagger.Subcomponent

@Subcomponent(modules = [HostModule::class])
@HostScope
interface HostComponent {

    fun provideDefaultComponent(): MovieListComponent

    fun provideMovieDescriptionComponent(): MovieDescriptionComponent

    fun inject(activity: HostActivity)

}