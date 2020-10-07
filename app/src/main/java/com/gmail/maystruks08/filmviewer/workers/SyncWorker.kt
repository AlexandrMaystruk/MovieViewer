package com.gmail.maystruks08.filmviewer.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gmail.maystruks08.data.local.dao.DefaultDao
import com.gmail.maystruks08.data.remote.DefaultApi
import com.gmail.maystruks08.domain.entities.ResultOfTask
import com.gmail.maystruks08.filmviewer.App
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import javax.inject.Inject

class SyncWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    @Inject
    lateinit var defaultDao: DefaultDao

    @Inject
    lateinit var defaultApi: DefaultApi

    override suspend fun doWork(): Result = coroutineScope {
        Timber.i("Sync runners worker STARTED")
        App.hostComponent?.inject(this@SyncWorker)
        val resultOfTask = ResultOfTask.build {
           //TODO do any action here
        }
        when (resultOfTask) {
            is ResultOfTask.Value -> Result.success()
            is ResultOfTask.Error -> Result.retry()
            else -> Result.failure()
        }
    }
}