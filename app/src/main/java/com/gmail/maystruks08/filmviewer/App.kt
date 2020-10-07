package com.gmail.maystruks08.filmviewer

import android.app.Application
import com.gmail.maystruks08.data.BuildConfig
import com.gmail.maystruks08.filmviewer.core.di.AppModule
import com.gmail.maystruks08.filmviewer.core.di.BaseComponent
import com.gmail.maystruks08.filmviewer.core.di.DaggerBaseComponent
import com.gmail.maystruks08.filmviewer.core.di.host.HostComponent
import com.gmail.maystruks08.filmviewer.core.di.def.DefaultComponent
import com.gmail.maystruks08.filmviewer.utils.TimberFileTree
import timber.log.Timber

class App : Application() {

    companion object {

        lateinit var baseComponent: BaseComponent

        var hostComponent: HostComponent? = null
            get() {
                if (field == null)
                    field = baseComponent.provideHostComponent()
                return field
            }

        var defaultComponent: DefaultComponent? = null
            get() {
                if (field == null)
                    field = hostComponent?.provideDefaultComponent()
                return field
            }

        fun clearHostComponent() {
            hostComponent = null
        }

        fun clearDefaultComponent() {
            defaultComponent = null
        }

    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(TimberFileTree(this))

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        baseComponent = DaggerBaseComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        baseComponent.inject(this)
    }
}