package com.gmail.maystruks08.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.gmail.maystruks08.data.local.entity.MovieTable

@Dao
interface MovieDao : BaseDao<MovieTable> {

    @Query("SELECT * FROM movie_table")
    fun getMovieList(): List<MovieTable>

    @Query("DELETE FROM movie_table")
    fun deleteDefaultTable()

}


