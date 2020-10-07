package com.gmail.maystruks08.data.repository

import java.util.concurrent.TimeUnit

interface SyncDataScheduler {

    fun startSyncData(period: Long, timeUnit: TimeUnit)

    fun stopAllWork()

}