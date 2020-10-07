package com.gmail.maystruks08.filmviewer.core.di.host

import com.gmail.maystruks08.filmviewer.HostActivity
import com.gmail.maystruks08.filmviewer.core.di.NavigationModule
import com.gmail.maystruks08.filmviewer.core.di.SyncModule
import com.gmail.maystruks08.filmviewer.core.di.def.DefaultComponent
import com.gmail.maystruks08.filmviewer.workers.SyncWorker
import dagger.Subcomponent

@Subcomponent(modules = [HostModule::class, NavigationModule::class, SyncModule::class])
@HostScope
interface HostComponent {

    fun provideDefaultComponent(): DefaultComponent

    fun inject(activity: HostActivity)

    fun inject(workHelper: SyncWorker)

}