package com.gmail.maystruks08.filmviewer.core.di

import com.gmail.maystruks08.data.ImageLoaderImpl
import com.gmail.maystruks08.domain.repository.ImageLoader
import com.gmail.maystruks08.domain.util.NetworkUtil
import com.gmail.maystruks08.filmviewer.utils.NetworkUtilImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindNetworkUtil(impl: NetworkUtilImpl): NetworkUtil

    @Binds
    @Singleton
    abstract fun provideImageLoader(imageLoaderImpl: ImageLoaderImpl): ImageLoader

}