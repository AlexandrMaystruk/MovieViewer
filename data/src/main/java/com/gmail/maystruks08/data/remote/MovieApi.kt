package com.gmail.maystruks08.data.remote

import com.gmail.maystruks08.data.remote.pojo.MoviePojo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface MovieApi {

    @GET("/test.json")
    fun getMovieList(): Call<List<MoviePojo>>

    @Streaming
    @GET
    suspend fun downloadFile(@Url fileUrl:String): Response<ResponseBody>

}