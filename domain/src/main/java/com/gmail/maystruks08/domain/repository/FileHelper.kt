package com.gmail.maystruks08.domain.repository

interface FileHelper {

    suspend fun load(id: String, url: String): ByteArray?

}