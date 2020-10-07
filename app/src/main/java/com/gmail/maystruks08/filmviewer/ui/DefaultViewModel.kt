package com.gmail.maystruks08.filmviewer.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.maystruks08.domain.exception.SaveDataException
import com.gmail.maystruks08.domain.exception.SyncWithServerException
import com.gmail.maystruks08.domain.interactors.DefaultInteractor
import com.gmail.maystruks08.filmviewer.core.base.BaseViewModel
import com.gmail.maystruks08.filmviewer.ui.viewmodels.DefaultView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

class DefaultViewModel @Inject constructor(
    private val defaultInteractor: DefaultInteractor
) : BaseViewModel() {

    val defaultViews get(): LiveData<MutableList<DefaultView>> = _defaultViewsLiveData

    private val _defaultViewsLiveData = MutableLiveData<MutableList<DefaultView>>()

    @ExperimentalCoroutinesApi
    fun initFragment() {


    }

    private fun handleError(e: Throwable) {
        Timber.e(e)
        when (e) {
            is SaveDataException -> toastLiveData.postValue(e.message)
            is SyncWithServerException -> toastLiveData.postValue(e.message)
        }
    }
}