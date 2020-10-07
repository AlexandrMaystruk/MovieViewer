package com.gmail.maystruks08.data.local

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigPreferences @Inject constructor(context: Context) {

    companion object {

        private const val NAME = "CONFIG_PREFS"

    }

    private val sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

}