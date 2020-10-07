package com.gmail.maystruks08.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gmail.maystruks08.data.local.dao.DefaultDao
import com.gmail.maystruks08.data.local.entity.DefaultTable

@Database(entities = [DefaultTable::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun defaultDao(): DefaultDao

}