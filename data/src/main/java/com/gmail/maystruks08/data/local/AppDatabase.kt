package com.gmail.maystruks08.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.maystruks08.data.local.dao.MovieDao
import com.gmail.maystruks08.data.local.entity.MovieTable

@Database(entities = [MovieTable::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun defaultDao(): MovieDao

}