package com.qdream.retrofit_kotlin_coroutines_example.ui.login

import android.content.Context
import androidx.lifecycle.*
import com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.request.LoginRequest
import com.qdream.retrofit_kotlin_coroutines_example.data.networkModel.response.LoginResponse
import com.qdream.retrofit_kotlin_coroutines_example.data.repository.MainRepository
import com.qdream.retrofit_kotlin_coroutines_example.model.User
import com.qdream.retrofit_kotlin_coroutines_example.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(private val mainRepository: MainRepository) : ViewModel() {


    private val loginResponse = MutableLiveData<Resource<Boolean>>()

    fun login(): LiveData<Resource<Boolean>> {
        return loginResponse
    }

    fun loginToServer(userName: String, password: String) {
        viewModelScope.launch {
            if (userName.isEmpty() || password.isEmpty())
                loginResponse.postValue(Resource.error(null, "complete data"))
            else {
                try {
                    loginResponse.postValue(Resource.loading(null))

                    val response = mainRepository.loginRepo(LoginRequest(userName, password))

                    if (response.flag == "100") {// success get data , save User Data on db
                        loginResponse.postValue(
                            Resource.success(
                                saveUserLocal(response)
                            )
                        )
                    } else
                        loginResponse.postValue(
                            Resource.error(
                                null,
                                response.message
                            )
                        )//when user enter error data

                } catch (e: Exception) {
                    loginResponse.postValue(Resource.error(null, e.toString()))
                }
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