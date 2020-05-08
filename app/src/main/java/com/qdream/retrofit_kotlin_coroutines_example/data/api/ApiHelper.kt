package com.qdream.retrofit_kotlin_coroutines_example.data.api

import com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.request.LoginRequest


class ApiHelper (private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
    suspend fun login(request: LoginRequest)=apiService.login(request)

}