package com.gmail.maystruks08.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieTable(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imagePath: String? = null,
    val description: String,
    val time: Long
)




