package com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.request

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("UserName")
    val userName: String,
    @SerializedName("Password")
    val password: String
)