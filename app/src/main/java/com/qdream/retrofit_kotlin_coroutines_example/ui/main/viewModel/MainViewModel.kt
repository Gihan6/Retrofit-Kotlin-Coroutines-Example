package com.qdream.retrofit_kotlin_coroutines_example.ui.main.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.qdream.retrofit_kotlin_coroutines_example.data.repository.MainRepository
import com.qdream.retrofit_kotlin_coroutines_example.util.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {


    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = mainRepository.getUsers()
            if (response.isEmpty()) {

            }
            emit(Resource.success(data = response))
        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = exception.message ?: "Error Occurred!$exception"
                )
            )
        }
    }
    //
}