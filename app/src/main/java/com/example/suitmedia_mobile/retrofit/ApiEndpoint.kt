package com.example.suitmedia_mobile.retrofit

import com.example.suitmedia_mobile.retrofit.dataClass.RawResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {
    @GET("users")
    suspend fun getRawResponse(@Query("page") page: Int, @Query("per_page") per_page: Int) : Response<RawResponse>
//    page=1&per_page=10
}