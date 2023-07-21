package com.study.studyretrofit

//import okhttp3.Call

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ServiceInterface {
    @Headers("Content-Type:application/json")
    @GET("/products")
    fun getAllProducts(): Call<ApiResponse>
}