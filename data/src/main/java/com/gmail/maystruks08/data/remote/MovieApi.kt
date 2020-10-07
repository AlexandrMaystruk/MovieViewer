package com.gmail.maystruks08.data.remote

import com.gmail.maystruks08.data.remote.pojo.DefaultPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Call<List<DefaultPojo>>

}