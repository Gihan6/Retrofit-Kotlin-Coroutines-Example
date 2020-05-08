package com.qdream.retrofit_kotlin_coroutines_example.data.repository

import android.content.Context
import android.util.Log
import com.qdream.retrofit_kotlin_coroutines_example.data.api.ApiHelper
import com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.request.LoginRequest
import com.qdream.retrofit_kotlin_coroutines_example.db.DataBase
import com.qdream.retrofit_kotlin_coroutines_example.model.User
import com.qdream.retrofit_kotlin_coroutines_example.model.User.Companion.loggedUser
import com.qdream.retrofit_kotlin_coroutines_example.ui.main.viewModel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject


class MainRepository(private val apiHelper: ApiHelper) : KoinComponent {

    private val dataBase by inject<DataBase>()


    suspend fun getUsers() = apiHelper.getUsers()

    suspend fun loginRepo(request: LoginRequest) = apiHelper.login(request)

    suspend fun saveLoginUser(userLogged: User): Boolean {
        return try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                dataBase.companyDao().insert(userLogged)
            }
            true
        } catch (e: Exception) {
            Log.e("dataBase", e.toString())
            false
        }


    }

    suspend fun checkIfUserLogin(): Boolean {
        return try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                loggedUser = dataBase.companyDao().checkIfUserExist()
            }
            true
        } catch (e: Exception) {
            Log.e("dataBase", e.toString())
            false
        }


    }
}