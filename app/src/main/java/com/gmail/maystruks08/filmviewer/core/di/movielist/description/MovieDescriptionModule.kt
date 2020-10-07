package com.gmail.maystruks08.filmviewer.core.di.movielist.description

import androidx.lifecycle.ViewModel
import com.gmail.maystruks08.data.repository.MovieRepositoryImpl
import com.gmail.maystruks08.domain.interactors.MovieInteractor
import com.gmail.maystruks08.domain.interactors.MovieInteractorImpl
import com.gmail.maystruks08.domain.repository.MovieRepository
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.ViewModelKey
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.ViewModelModule
import com.gmail.maystruks08.filmviewer.ui.description.MovieDescriptionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class MovieDescriptionModule {

    @IntoMap
    @Binds
    @MovieListDescriptionScope
    @ViewModelKey(MovieDescriptionViewModel::class)
    abstract fun bindViewModel(viewModel: MovieDescriptionViewModel): ViewModel

    @Binds
    @MovieListDescriptionScope
    abstract fun bindDefaultInteractor(impl: MovieInteractorImpl): MovieInteractor

    @Binds
    @MovieListDescriptionScope
    abstract fun bindDefaultRepository(impl: MovieRepositoryImpl): MovieRepository

}