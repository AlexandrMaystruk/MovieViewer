package com.gmail.maystruks08.filmviewer.core.di.host

import androidx.lifecycle.ViewModel
import com.gmail.maystruks08.filmviewer.HostViewModel
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.ViewModelKey
import com.gmail.maystruks08.filmviewer.core.di.viewmodel.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class HostModule {

    @IntoMap
    @Binds
    @HostScope
    @ViewModelKey(HostViewModel::class)
    abstract fun bindHostViewModel(viewModel: HostViewModel): ViewModel

}