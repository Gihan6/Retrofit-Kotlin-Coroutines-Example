package com.qdream.retrofit_kotlin_coroutines_example.ui.main.viewModel


import androidx.lifecycle.*
import com.qdream.retrofit_kotlin_coroutines_example.data.repository.MainRepository
import com.qdream.retrofit_kotlin_coroutines_example.model.User
import com.qdream.retrofit_kotlin_coroutines_example.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val users = MutableLiveData<Resource<List<User>>>()
    fun getUsers(): LiveData<Resource<List<User>>> {
        return users
    }

    fun getUsersFromWebServices()  {
        viewModelScope.launch {
            users.postValue(Resource.loading(data = null))
            try {
                val response = mainRepository.getUsers()
                if (response.isEmpty()) {

                }
                users.postValue(Resource.success(data = response))
            } catch (exception: Exception) {
                users.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!$exception"
                    )
                )
            }
        }
    }
    //
}