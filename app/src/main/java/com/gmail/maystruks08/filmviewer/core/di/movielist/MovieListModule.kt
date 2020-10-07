package com.gmail.maystruks08.filmviewer.core.di.movielist

import androidx.lifecycle.ViewModel
import com.gmail.maystruks08.data.repository.MovieRepositoryImpl
import com.gmail.maystruks08.domain.interactors.MovieInteractor
import com.gmail.maystruks08.domain.interactors.MovieInteractorImpl
import com.gmail.maystruks08.domain.repository.MovieRepository
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.ViewModelKey
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.ViewModelModule
import com.gmail.maystruks08.filmviewer.ui.movielist.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class MovieListModule {

    @IntoMap
    @Binds
    @MovieListScope
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindViewModel(viewModel: MovieListViewModel): ViewModel

    @Binds
    @MovieListScope
    abstract fun bindDefaultInteractor(impl: MovieInteractorImpl): MovieInteractor

    @Binds
    @MovieListScope
    abstract fun bindDefaultRepository(impl: MovieRepositoryImpl): MovieRepository

}