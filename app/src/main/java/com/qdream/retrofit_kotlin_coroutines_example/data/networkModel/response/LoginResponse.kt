package com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.response

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("Flag")
     val flag: String,
    @SerializedName("Message")
     val message: String,
    @SerializedName("Record")
     val record: Record
)

class Record(
    @SerializedName("Id")
     val id: String,
    @SerializedName("UserName")
     val userName: String,
    @SerializedName("Name")
     val name: String,
    @SerializedName("Email")
     val email: String,
    @SerializedName("Password")
     val password: String,
    @SerializedName("UserImage")
     val userImage: String,
    @SerializedName("GroupId")
     val groupId: String,
    @SerializedName("Verified")
     val verified: String
)