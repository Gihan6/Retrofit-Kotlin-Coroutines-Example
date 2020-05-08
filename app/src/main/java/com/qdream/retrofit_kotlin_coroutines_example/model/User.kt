package com.qdream.retrofit_kotlin_coroutines_example.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User")

data class User(
    @PrimaryKey
    @ColumnInfo(name = "Id")
    @SerializedName("Id")
    val id: String,
    @ColumnInfo(name = "UserName")
    @SerializedName("UserName")
    val userName: String,
    @ColumnInfo(name = "Name")
    @SerializedName("Name")
    val name: String,
    @ColumnInfo(name = "Email")
    @SerializedName("Email")
    val email: String,
    @ColumnInfo(name = "Password")
    @SerializedName("Password")
    val password: String,
    @ColumnInfo(name = "UserImage")
    @SerializedName("UserImage")
    val userImage: String,
    @ColumnInfo(name = "GroupId")
    @SerializedName("GroupId")
    val groupId: String,
    @ColumnInfo(name = "Verified")
    @SerializedName("Verified")
    val verified: String,
    @ColumnInfo(name = "active")
    @SerializedName("active")
    val active: Boolean = false

) {
    companion object {
        lateinit var loggedUser: User
    }

    init {
        loggedUser = this
    }
}
