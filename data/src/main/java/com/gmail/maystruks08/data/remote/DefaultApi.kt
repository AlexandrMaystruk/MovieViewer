package com.gmail.maystruks08.data.remote

import com.gmail.maystruks08.data.remote.pojo.DefaultPojo

interface DefaultApi{

    suspend fun getDefaultEntityPojo(): List<DefaultPojo>

}