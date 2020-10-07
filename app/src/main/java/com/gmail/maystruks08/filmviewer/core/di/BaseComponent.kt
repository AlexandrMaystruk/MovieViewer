package com.gmail.maystruks08.filmviewer.core.di

import android.content.Context
import android.content.res.Resources
import com.gmail.maystruks08.filmviewer.App
import com.gmail.maystruks08.filmviewer.core.di.host.HostComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, NetworkModule::class, LogModule::class, APIModule::class])
interface BaseComponent {

    fun provideAppContext(): Context

    fun provideResources(): Resources

    fun provideHostComponent(): HostComponent

    fun inject(app: App)
}