package com.gmail.maystruks08.domain.repository

interface ImageLoader {

    suspend fun load(id: String, url: String): ByteArray?

}