package com.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.model.Status
import com.test.userapp.R
import com.ui.viewmodel.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    companion object {
        private const val SPLASH_DISPLAY_LENGTH = 3000L
    }

    private lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)

        Handler().postDelayed({
            viewModel.getUsers()
        }, SPLASH_DISPLAY_LENGTH)

        setObserver()
    }

    private fun setObserver() {
        viewModel.getUsersObserver().observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                }

                Status.SUCCESS -> {
                    startActivity(Intent(this, UserListActivity::class.java))
                    finish()
                }

                Status.ERROR -> {
                }
            }
        })
    }
}