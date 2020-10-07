package com.gmail.maystruks08.filmviewer

import android.app.Application
import com.gmail.maystruks08.filmviewer.core.di.AppModule
import com.gmail.maystruks08.filmviewer.core.di.BaseComponent
import com.gmail.maystruks08.filmviewer.core.di.DaggerBaseComponent
import com.gmail.maystruks08.filmviewer.core.di.host.HostComponent
import com.gmail.maystruks08.filmviewer.core.di.movielist.MovieListComponent
import com.gmail.maystruks08.filmviewer.core.di.movielist.description.MovieDescriptionComponent
import com.gmail.maystruks08.filmviewer.utils.TimberFileTree
import com.squareup.leakcanary.BuildConfig
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

        var movieListComponent: MovieListComponent? = null
            get() {
                if (field == null)
                    field = hostComponent?.provideDefaultComponent()
                return field
            }

        var movieDescriptionComponent: MovieDescriptionComponent? = null
            get() {
                if (field == null)
                    field = hostComponent?.provideMovieDescriptionComponent()
                return field
            }

        fun clearHostComponent() {
            hostComponent = null
        }

        fun clearMovieListComponent() {
            movieListComponent = null
        }

        fun clearMovieDescriptionComponent() {
            movieDescriptionComponent = null
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