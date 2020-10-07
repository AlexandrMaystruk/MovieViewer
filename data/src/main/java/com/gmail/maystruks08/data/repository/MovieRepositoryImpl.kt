package com.gmail.maystruks08.data.repository

import com.gmail.maystruks08.data.cache.MovieCache
import com.gmail.maystruks08.data.local.dao.MovieDao
import com.gmail.maystruks08.data.mappers.toEntity
import com.gmail.maystruks08.data.mappers.toTableEntity
import com.gmail.maystruks08.data.remote.MovieApi
import com.gmail.maystruks08.domain.entities.Movie
import com.gmail.maystruks08.domain.exception.SyncWithServerException
import com.gmail.maystruks08.domain.repository.MovieRepository
import com.gmail.maystruks08.domain.util.NetworkUtil
import retrofit2.await
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkUtil: NetworkUtil,
    private val api: MovieApi,
    private val dao: MovieDao,
    private val cache: MovieCache
) : MovieRepository {

    override suspend fun getMovieListFromRemote(): List<Movie> {
        return if (networkUtil.isOnline()) {
            api.getMovieList().await().map { it.toEntity() }
        } else {
            throw SyncWithServerException()
        }
    }

    override suspend fun getMovieListFromCache(): List<Movie> {
        return if (cache.movieEntityList.isEmpty()) {
            cache.movieEntityList.apply {
                val moviesFromDatabase = dao.getMovieList().map { it.toEntity() }
                clear()
                addAll(moviesFromDatabase)
            }
        } else cache.movieEntityList
    }

    override suspend fun cacheMovies(movies: List<Movie>) {
        val moviesTable = movies.map { it.toTableEntity() }
        dao.insertAllOrReplace(moviesTable)
        cache.movieEntityList = movies.toMutableList()
    }

    override suspend fun getLoadImageFromRemote() {
        TODO("Not yet implemented")
    }

    override suspend fun getStoreImage() {
        TODO("Not yet implemented")
    }

    override suspend fun clearImageCache() {
        TODO("Not yet implemented")
    }

}