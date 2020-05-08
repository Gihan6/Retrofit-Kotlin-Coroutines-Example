package com.qdream.retrofit_kotlin_coroutines_example.di

import com.qdream.retrofit_kotlin_coroutines_example.data.api.ApiHelper
import com.qdream.retrofit_kotlin_coroutines_example.data.api.ApiService
import com.qdream.retrofit_kotlin_coroutines_example.data.api.RetrofitBuilder
import com.qdream.retrofit_kotlin_coroutines_example.data.repository.MainRepository
import com.qdream.retrofit_kotlin_coroutines_example.db.DataBase
import com.qdream.retrofit_kotlin_coroutines_example.ui.login.LoginViewModel
import com.qdream.retrofit_kotlin_coroutines_example.ui.main.viewModel.MainViewModel
import com.qdream.retrofit_kotlin_coroutines_example.ui.splach.SplashViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { DataBase.getInstance(get()) }
    factory { RetrofitBuilder.apiService }
    factory { ApiHelper(get()) }
    single { MainRepository(get()) }

}
val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }

}