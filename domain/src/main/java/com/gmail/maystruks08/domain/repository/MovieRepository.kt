package com.gmail.maystruks08.domain.repository

import com.gmail.maystruks08.domain.entities.Movie

interface MovieRepository {

    suspend fun getMovieListFromRemote(): List<Movie>

    suspend fun getMovieListFromCache(): List<Movie>

    suspend fun cacheMovies(movies: List<Movie>)

    suspend fun getLoadImageFromRemote()

    suspend fun getStoreImage()

    suspend fun clearImageCache()

}