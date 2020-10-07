package com.gmail.maystruks08.filmviewer.core.di.def

import com.gmail.maystruks08.filmviewer.ui.DefaultFragment
import dagger.Subcomponent

@Subcomponent(modules = [DefaultModule::class])
@DefaultScope

interface DefaultComponent {

    fun inject(fragment: DefaultFragment)

}