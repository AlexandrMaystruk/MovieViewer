package com.gmail.maystruks08.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "default_table")
data class DefaultTable(
    @PrimaryKey
    val id: Int,
)




