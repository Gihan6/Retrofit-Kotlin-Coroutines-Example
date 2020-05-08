package com.qdream.retrofit_kotlin_coroutines_example.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.qdream.retrofit_kotlin_coroutines_example.db.DataBase
import com.qdream.retrofit_kotlin_coroutines_example.ui.main.viewModel.MainViewModel
import com.qdream.retrofit_kotlin_coroutines_example.ui.splach.SplashViewModel
import org.koin.dsl.module

object DependencyModules {

    val appModules = module {

        factory { DataBase.getInstance( get()) }

//        viewModel { MainViewModel( get()) }
    }
}