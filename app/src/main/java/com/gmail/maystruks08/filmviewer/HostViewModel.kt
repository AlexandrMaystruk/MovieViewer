package com.gmail.maystruks08.filmviewer

import com.gmail.maystruks08.data.repository.SyncDataScheduler
import com.gmail.maystruks08.filmviewer.core.base.BaseViewModel
import com.gmail.maystruks08.filmviewer.core.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class HostViewModel @Inject constructor(
   private val router: Router,
    private val syncDataScheduler: SyncDataScheduler
) : BaseViewModel() {

    init {
        router.newRootScreen(Screens.DefaultScreen())
    }

    override fun onCleared() {
        syncDataScheduler.stopAllWork()
        super.onCleared()
    }

    fun onExitClicked() {
        router.exit()
    }
}