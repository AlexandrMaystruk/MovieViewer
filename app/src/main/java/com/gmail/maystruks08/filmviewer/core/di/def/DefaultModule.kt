package com.gmail.maystruks08.filmviewer.core.di.def

import androidx.lifecycle.ViewModel
import com.gmail.maystruks08.data.repository.DefaultRepositoryImpl
import com.gmail.maystruks08.domain.interactors.DefaultInteractor
import com.gmail.maystruks08.domain.interactors.DefaultInteractorImpl
import com.gmail.maystruks08.domain.repository.DefaultRepository
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.ViewModelKey
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.ViewModelModule
import com.gmail.maystruks08.filmviewer.ui.DefaultViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class DefaultModule {

    @IntoMap
    @Binds
    @DefaultScope
    @ViewModelKey(DefaultViewModel::class)
    abstract fun bindViewModel(viewModel: DefaultViewModel): ViewModel

    @Binds
    @DefaultScope
    abstract fun bindDefaultInteractor(impl: DefaultInteractorImpl): DefaultInteractor

    @Binds
    @DefaultScope
    abstract fun bindDefaultRepository(impl: DefaultRepositoryImpl): DefaultRepository

}