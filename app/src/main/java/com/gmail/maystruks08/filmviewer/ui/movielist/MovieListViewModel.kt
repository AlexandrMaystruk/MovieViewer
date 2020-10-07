package com.gmail.maystruks08.filmviewer.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gmail.maystruks08.domain.entities.Movie
import com.gmail.maystruks08.domain.entities.ResultOfTask
import com.gmail.maystruks08.domain.exception.SaveDataException
import com.gmail.maystruks08.domain.exception.SyncWithServerException
import com.gmail.maystruks08.domain.interactors.MovieInteractor
import com.gmail.maystruks08.filmviewer.core.base.BaseViewModel
import com.gmail.maystruks08.filmviewer.ui.viewmodels.MovieView
import com.gmail.maystruks08.filmviewer.ui.viewmodels.toMovieView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor
) : BaseViewModel() {

    val movieViews get(): LiveData<MutableList<MovieView>> = _movieViewsLiveData

    private val _movieViewsLiveData = MutableLiveData<MutableList<MovieView>>()

    val progressBar get(): LiveData<Boolean> = _progressBarLiveData

    private val _progressBarLiveData = MutableLiveData<Boolean>()

    init {
        loadMovieData()
    }

    private fun loadMovieData(){
        viewModelScope.launch (Dispatchers.IO) {
            movieInteractor.provideMovieList().collect { moviesTask->
                when(moviesTask){
                    is ResultOfTask.Value -> handleMovies(moviesTask.value)
                    is ResultOfTask.Loading -> showLoading(true)
                    is ResultOfTask.Error -> handleError(moviesTask.error)
                }
            }
        }
    }


    fun refreshData(){


    }

    private fun handleMovies(movies: List<Movie>){
        Timber.e("handleMovies ${movies.size}")
        showLoading(false)
        val moviesViews = movies.map { it.toMovieView() }.toMutableList()
        _movieViewsLiveData.postValue(moviesViews)
    }

    private fun showLoading(isShow: Boolean){
        Timber.e("showLoading ${isShow}")
        _progressBarLiveData.postValue(isShow)
    }

    private fun handleError(e: Throwable) {
        showLoading(false)
        when (e) {
            is SaveDataException -> _toastLiveData.postValue(e.message)
            is SyncWithServerException -> _toastLiveData.postValue(e.message)
        }
        Timber.e(e)
    }
}