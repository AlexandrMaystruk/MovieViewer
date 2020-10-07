package com.gmail.maystruks08.filmviewer.ui.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gmail.maystruks08.domain.entities.ResultOfTask
import com.gmail.maystruks08.domain.exception.SaveDataException
import com.gmail.maystruks08.domain.exception.SyncWithServerException
import com.gmail.maystruks08.domain.interactors.MovieInteractor
import com.gmail.maystruks08.filmviewer.core.base.BaseViewModel
import com.gmail.maystruks08.filmviewer.ui.viewmodels.MovieFullInfoView
import com.gmail.maystruks08.filmviewer.ui.viewmodels.toMovieFullInfoView
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieDescriptionViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor
) : BaseViewModel() {

    val movie get(): LiveData<MovieFullInfoView> = _movieLiveData

    private val _movieLiveData = MutableLiveData<MovieFullInfoView>()

    fun initFragment(movieId: Int) {
        viewModelScope.launch {
            when(val task = movieInteractor.provideMovieById(movieId)){
                is ResultOfTask.Value -> {
                    _movieLiveData.postValue(task.value.toMovieFullInfoView())
                }
                is ResultOfTask.Error -> handleError(task.error)
            }
        }
    }

    private fun handleError(e: Throwable) {
        Timber.e(e)
        when (e) {
            is SaveDataException -> _toastLiveData.postValue(e.message)
            is SyncWithServerException -> _toastLiveData.postValue(e.message)
        }
    }
}