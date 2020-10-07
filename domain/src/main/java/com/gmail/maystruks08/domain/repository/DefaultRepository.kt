package com.gmail.maystruks08.domain.repository

import com.gmail.maystruks08.domain.entities.DefaultEntity

interface DefaultRepository {

    suspend fun getEntityList(): List<DefaultEntity>

}