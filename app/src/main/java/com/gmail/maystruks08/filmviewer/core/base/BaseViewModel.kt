package com.gmail.maystruks08.filmviewer.core.base

import androidx.lifecycle.*

abstract class BaseViewModel : ViewModel() {

    val toast get(): LiveData<String>  = toastLiveData

    protected val toastLiveData = MutableLiveData<String>()

}