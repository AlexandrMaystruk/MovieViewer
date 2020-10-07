package com.gmail.maystruks08.filmviewer.core.di

import com.gmail.maystruks08.data.LogHelperImpl
import com.gmail.maystruks08.domain.util.LogHelper
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class LogModule {

    @Binds
    @Singleton
    abstract fun provideLogHelper(logHelperImpl: LogHelperImpl): LogHelper

}