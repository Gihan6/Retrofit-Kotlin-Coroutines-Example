package com.qdream.retrofit_kotlin_coroutines_example.data.repository

import android.content.Context
import android.util.Log
import com.qdream.retrofit_kotlin_coroutines_example.data.api.ApiHelper
import com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.request.LoginRequest
import com.qdream.retrofit_kotlin_coroutines_example.db.DataBase
import com.qdream.retrofit_kotlin_coroutines_example.model.User
import com.qdream.retrofit_kotlin_coroutines_example.model.User.Companion.loggedUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

    suspend fun loginRepo(request: LoginRequest) = apiHelper.login(request)

    suspend fun saveLoginUser(userLogged: User, context: Context): Boolean {
        return try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                DataBase.getInstance(context).companyDao().insert(userLogged)
            }
            true
        } catch (e: Exception) {
            Log.e("dataBase", e.toString())
            false
        }


    }

    suspend fun checkIfUserLogin(context: Context): Boolean {
        return try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                loggedUser = DataBase.getInstance(context).companyDao().checkIfUserExist()
            }
            true
        } catch (e: Exception) {
            Log.e("dataBase", e.toString())
            false
        }


    }
}