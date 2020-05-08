package com.qdream.retrofit_kotlin_coroutines_example.ui.splach

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.qdream.retrofit_kotlin_coroutines_example.data.repository.MainRepository
import com.qdream.retrofit_kotlin_coroutines_example.util.Resource
import kotlinx.coroutines.Dispatchers

class SplashViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun checkLoggedUser(context: Context) = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success(mainRepository.checkIfUserLogin(context)))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!$exception"
                )
            )
        }
    }

}