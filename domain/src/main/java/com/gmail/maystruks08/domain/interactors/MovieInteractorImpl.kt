package com.gmail.maystruks08.domain.interactors

import com.gmail.maystruks08.domain.entities.Movie
import com.gmail.maystruks08.domain.entities.ResultOfTask
import com.gmail.maystruks08.domain.repository.MovieRepository
import com.gmail.maystruks08.domain.util.LogHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class MovieInteractorImpl @Inject constructor(
    private val movieRepository: MovieRepository,
    private val logHelper: LogHelper
) : MovieInteractor {


    @ExperimentalCoroutinesApi
    override suspend fun provideMovieList(): Flow<ResultOfTask<Exception, List<Movie>>> {
        return channelFlow {
            val cachedData = ResultOfTask.build { movieRepository.getMovieListFromCache() }
            this.offer(cachedData)
            val dataFomRemote = ResultOfTask.build { return@build updateCache() }
            this.offer(dataFomRemote)
        }
    }

    override suspend fun provideMovieById(id: Int): ResultOfTask<Exception, Movie> {
        return ResultOfTask.build {
            val movies = movieRepository.getMovieListFromCache()
            if (movies.isEmpty()) {
                val newData = updateCache()
                return@build newData.firstOrNull { it.id == id } ?: throw IllegalStateException("Movie not found!")
            } else {
                var movie = movies.firstOrNull { it.id == id }
                if (movie == null) {
                    val newData = updateCache()
                    movie = newData.firstOrNull { it.id == id }
                }
                return@build movie ?: throw IllegalStateException("Movie not found!")
            }
        }
    }

    private suspend fun updateCache(): List<Movie> {
        val newData = movieRepository.getMovieListFromRemote()
        movieRepository.cacheMovies(newData)
        return newData
    }
}