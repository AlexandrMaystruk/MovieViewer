package com.gmail.maystruks08.filmviewer.ui.description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gmail.maystruks08.domain.entities.ResultOfTask
import com.gmail.maystruks08.domain.exception.SaveDataException
import com.gmail.maystruks08.domain.exception.SyncWithServerException
import com.gmail.maystruks08.domain.interactors.MovieInteractor
import com.gmail.maystruks08.domain.repository.ImageLoader
import com.gmail.maystruks08.filmviewer.core.base.BaseViewModel
import com.gmail.maystruks08.filmviewer.core.ext.SingleLiveEvent
import com.gmail.maystruks08.filmviewer.core.ext.decodeBitmap
import com.gmail.maystruks08.filmviewer.ui.viewmodels.MovieFullInfoView
import com.gmail.maystruks08.filmviewer.ui.viewmodels.toMovieFullInfoView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieDescriptionViewModel @Inject constructor(
    private val imageLoader: ImageLoader,
    private val movieInteractor: MovieInteractor
) : BaseViewModel() {

    val movie get(): LiveData<MovieFullInfoView> = _movieLiveData
    private val _movieLiveData = MutableLiveData<MovieFullInfoView>()

    val progressBar get(): LiveData<Boolean> = _progressBarLiveData
    private val _progressBarLiveData = SingleLiveEvent<Boolean>()

    fun initFragment(movieId: Int) {
        viewModelScope.launch {
            when(val task = movieInteractor.provideMovieById(movieId)){
                is ResultOfTask.Value -> {
                    _progressBarLiveData.postValue(false)
                    val movie = task.value
                    val imageDeferred = async(Dispatchers.IO) { movie.image?.let { imageLoader.load(movie.id.toString(), it)?.decodeBitmap() } }
                    val image = imageDeferred.await()
                    _movieLiveData.postValue(task.value.toMovieFullInfoView(image))
                }
                is ResultOfTask.Loading -> _progressBarLiveData.postValue(true)
                is ResultOfTask.Error -> handleError(task.error)
            }
        }
    }

    private fun handleError(e: Throwable) {
        _progressBarLiveData.postValue(false)
        Timber.e(e)
        when (e) {
            is SaveDataException -> _toastLiveData.postValue(e.message)
            is SyncWithServerException -> _toastLiveData.postValue(e.message)
        }
    }
}