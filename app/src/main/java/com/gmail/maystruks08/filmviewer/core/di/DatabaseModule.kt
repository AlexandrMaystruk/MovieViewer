package com.gmail.maystruks08.filmviewer.core.di

import android.content.Context
import androidx.room.Room
import com.gmail.maystruks08.data.local.AppDatabase
import com.gmail.maystruks08.data.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @JvmStatic
    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "default_db").fallbackToDestructiveMigration().build()

    @JvmStatic
    @Provides
    @Singleton
    fun defaultDao(appDatabase: AppDatabase): MovieDao = appDatabase.defaultDao()


}