package com.example.suitmedia_mobile.retrofit

import com.example.suitmedia_mobile.retrofit.dataClass.RawResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("users")
    suspend fun getRawResponse() : Response<RawResponse>
//    fun getRawResponse() : Call<RawResponse>
}