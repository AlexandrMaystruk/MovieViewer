package com.gmail.maystruks08.filmviewer.core.di

import com.gmail.maystruks08.data.repository.SyncDataScheduler
import com.gmail.maystruks08.filmviewer.core.di.host.HostScope
import com.gmail.maystruks08.filmviewer.workers.SyncWorkHelper
import dagger.Binds
import dagger.Module

@Module
abstract class SyncModule {

    @Binds
    @HostScope
    abstract fun provideSyncRunnersDataScheduler(syncRunnersWorkHelper: SyncWorkHelper): SyncDataScheduler

}