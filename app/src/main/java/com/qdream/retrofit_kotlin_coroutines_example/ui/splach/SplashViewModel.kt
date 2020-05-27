package com.qdream.retrofit_kotlin_coroutines_example.ui.splach


import androidx.lifecycle.*
import com.qdream.retrofit_kotlin_coroutines_example.data.repository.MainRepository
import com.qdream.retrofit_kotlin_coroutines_example.model.User
import com.qdream.retrofit_kotlin_coroutines_example.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val checkLogin = MutableLiveData<Resource<Boolean>>()


    fun checkLoggedUser(): LiveData<Resource<Boolean>> {
        return checkLogin
    }


    fun checkLoggedUserRequest() {
        viewModelScope.launch {
            try {
                checkLogin.postValue(
                    Resource.success(
                        data = mainRepository.checkIfUserLogin()
                    )
                )
            } catch (exception: Exception) {
                checkLogin.postValue(
                    Resource.error(
                        data = false,
                        message = exception.message ?: "Error Occurred!$exception"
                    )
                )
            }
        }

    }

}

