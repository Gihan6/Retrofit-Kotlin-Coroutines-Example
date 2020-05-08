package com.qdream.retrofit_kotlin_coroutines_example.data.api

import com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.request.LoginRequest
import com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.response.LoginResponse
import com.qdream.retrofit_kotlin_coroutines_example.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>


    @POST("login.php")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}