package com.gmail.maystruks08.filmviewer.core.navigation

import com.gmail.maystruks08.filmviewer.ui.DefaultFragment

object Screens {

    class DefaultScreen : AppScreen() {
        override fun getFragment() = DefaultFragment()

        companion object {
            fun tag() = DefaultFragment::class.java.canonicalName ?: ""
        }
    }
}

