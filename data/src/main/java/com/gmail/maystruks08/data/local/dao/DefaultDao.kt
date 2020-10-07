package com.gmail.maystruks08.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.gmail.maystruks08.data.local.entity.DefaultTable

@Dao
interface DefaultDao : BaseDao<DefaultTable> {

    @Query("SELECT * FROM default_table")
    fun getTableList(): List<DefaultTable>

    @Query("DELETE FROM default_table")
    fun deleteDefaultTable()

}


