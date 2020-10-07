package com.gmail.maystruks08.filmviewer.core.di

import com.gmail.maystruks08.data.local.AppDatabase
import com.gmail.maystruks08.data.local.dao.DefaultDao
import com.gmail.maystruks08.data.remote.DefaultApi
import com.gmail.maystruks08.data.remote.DefaultApiImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class APIModule {

    @Binds
    @Singleton
    abstract fun provideDefaultApi(defaultApiImpl: DefaultApiImpl): DefaultApi

    @Provides
    @Singleton
    fun defaultDao(appDatabase: AppDatabase): DefaultDao = appDatabase.defaultDao()

    @Provides
    @Singleton
    fun gson(): Gson = Gson()

    @Provides
    @Singleton
    fun retrofit(): Retrofit =  Retrofit.Builder()
        .baseUrl("http://test.php-cd.attractgroup.com/")
        .build()

}