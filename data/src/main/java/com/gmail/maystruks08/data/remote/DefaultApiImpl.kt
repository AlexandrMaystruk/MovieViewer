package com.gmail.maystruks08.data.remote

import com.gmail.maystruks08.data.remote.pojo.DefaultPojo
import javax.inject.Inject

class DefaultApiImpl @Inject constructor() : DefaultApi {

    override suspend fun getDefaultEntityPojo(): List<DefaultPojo> {
        TODO("Not yet implemented")
    }
}