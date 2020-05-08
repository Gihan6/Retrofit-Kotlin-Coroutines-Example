package com.qdream.retrofit_kotlin_coroutines_example.ui.splach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.qdream.retrofit_kotlin_coroutines_example.R
import com.qdream.retrofit_kotlin_coroutines_example.data.api.ApiHelper
import com.qdream.retrofit_kotlin_coroutines_example.data.api.RetrofitBuilder
import com.qdream.retrofit_kotlin_coroutines_example.model.User
import com.qdream.retrofit_kotlin_coroutines_example.model.User.Companion.loggedUser
import com.qdream.retrofit_kotlin_coroutines_example.ui.base.ViewModelFactory
import com.qdream.retrofit_kotlin_coroutines_example.ui.login.LoginActivity
import com.qdream.retrofit_kotlin_coroutines_example.ui.main.view.MainActivity
import com.qdream.retrofit_kotlin_coroutines_example.ui.main.viewModel.MainViewModel
import com.qdream.retrofit_kotlin_coroutines_example.util.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private val activityScope = CoroutineScope(Dispatchers.Main)
    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)

        setupViewModel()
        showSplash()


    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(SplashViewModel::class.java)
    }

    private fun showSplash() {
        activityScope.launch {
            delay(3000)
            checkUSer()
        }
    }

    private fun checkUSer() {
        viewModel.checkLoggedUser(applicationContext).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { result ->
                            if (result && loggedUser.active)
                                goToMain()
                            else
                                goToLogin()
                        }
                    }
                    Status.ERROR -> {
                        goToLogin()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun goToMain() {
        var intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToLogin() {
        var intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

}
