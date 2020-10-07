package com.gmail.maystruks08.domain.interactors

import com.gmail.maystruks08.domain.entities.Movie
import com.gmail.maystruks08.domain.entities.ResultOfTask
import kotlinx.coroutines.flow.Flow

interface MovieInteractor {

    suspend fun provideMovieList(): Flow<ResultOfTask<Exception, List<Movie>>>

    suspend fun provideMovieById(id: Int): ResultOfTask<Exception, Movie>

}