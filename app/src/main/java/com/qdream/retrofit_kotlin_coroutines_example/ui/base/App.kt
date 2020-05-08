package com.qdream.retrofit_kotlin_coroutines_example.ui.base

import android.app.Application

import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Unique initialization of Dependency Injection library to allow the use of application context
//        startKoin { androidContext(this@App) }

    }
}