package com.gmail.maystruks08.domain.util

interface NetworkUtil {

    fun isOnline(): Boolean

    fun subscribeToConnectionChange(key: String, onConnectionChanged: (Boolean) -> Unit)

    fun unsubscribe(key: String)

}