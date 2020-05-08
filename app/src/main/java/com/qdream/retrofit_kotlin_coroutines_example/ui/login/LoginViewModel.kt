package com.qdream.retrofit_kotlin_coroutines_example.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.request.LoginRequest
import com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.response.LoginResponse
import com.qdream.retrofit_kotlin_coroutines_example.data.repository.MainRepository
import com.qdream.retrofit_kotlin_coroutines_example.model.User
import com.qdream.retrofit_kotlin_coroutines_example.util.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class LoginViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun login(userName: String, password: String) = liveData(Dispatchers.IO) {

        if (userName.isEmpty() || password.isEmpty())
            emit(Resource.error(null, "complete data"))
        else {
            try {
                emit(Resource.loading(null))

                val response = mainRepository.loginRepo(LoginRequest(userName, password))

                if (response.flag == "100") // success get data , save User Data on db
                    emit(
                        Resource.success(
                            saveUserLocal(
                                response
                            )
                        )
                    )
                else
                    emit(Resource.error(null, response.message))//when user enter error data

            } catch (e: Exception) {
                emit(Resource.error(null, e.toString()))
            }
        }

    }

    private suspend fun saveUserLocal(response: LoginResponse): Boolean {
        return mainRepository.saveLoginUser(
            User(
                response.record.id,
                response.record.userName,
                response.record.name,
                response.record.email,
                response.record.password,
                response.record.userImage,
                response.record.groupId,
                response.record.verified,
                true
            )

        )
    }

}