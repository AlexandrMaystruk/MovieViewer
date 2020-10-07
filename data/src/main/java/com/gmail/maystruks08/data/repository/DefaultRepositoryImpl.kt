package com.gmail.maystruks08.data.repository

import com.gmail.maystruks08.data.cache.DefaultCache
import com.gmail.maystruks08.data.local.dao.DefaultDao
import com.gmail.maystruks08.data.remote.DefaultApi
import com.gmail.maystruks08.domain.entities.DefaultEntity
import com.gmail.maystruks08.domain.util.NetworkUtil
import com.gmail.maystruks08.domain.repository.DefaultRepository
import javax.inject.Inject

class DefaultRepositoryImpl @Inject constructor(
    private val networkUtil: NetworkUtil,
    private val defaultApi: DefaultApi,
    private val defaultDao: DefaultDao,
    private val defaultCache: DefaultCache
) : DefaultRepository {
    
    override suspend fun getEntityList(): List<DefaultEntity> {
        TODO("Not yet implemented")
    }

}