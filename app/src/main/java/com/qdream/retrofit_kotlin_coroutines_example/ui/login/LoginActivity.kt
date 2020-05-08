package com.qdream.retrofit_kotlin_coroutines_example.ui.login

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
import com.qdream.retrofit_kotlin_coroutines_example.ui.base.ViewModelFactory
import com.qdream.retrofit_kotlin_coroutines_example.ui.main.view.MainActivity
import com.qdream.retrofit_kotlin_coroutines_example.ui.splach.SplashViewModel
import com.qdream.retrofit_kotlin_coroutines_example.util.Status
import kotlinx.android.synthetic.main.activity_login_activirty.*

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activirty)

        setListener()
        setupViewModel()

    }

    private fun setListener() {
        btn_login.setOnClickListener { v -> login(v) }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(LoginViewModel::class.java)
    }

    private fun login(v: View?) {
        loginToServer(et_email.text.toString(), et_password.text.toString())
    }

    private fun loginToServer(userName: String, password: String) {
        showLoading()
        viewModel.login(userName, password, this).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        hideLoading()
                        if (it.data!!)
                            goToMain()
                        else
                            showToast("Error Try Again Later")

                    }
                    Status.ERROR -> {
                        hideLoading()
                        showToast(it.message.toString())
                    }
                    Status.LOADING -> {
                        showLoading()
                    }
                }
            }
        })
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        pb_login.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        pb_login.visibility = View.GONE
    }
}
