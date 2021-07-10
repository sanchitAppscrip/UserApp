package com.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.response.UserDto
import com.test.userapp.R
import com.test.userapp.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {
    companion object {
        private const val USER_DETAILS = "USER_DETAILS"
        fun getStartIntent(userResponse: UserDto, context: Context): Intent {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(USER_DETAILS, userResponse)
            return intent
        }
    }


    private lateinit var userDetails: UserDto
    private lateinit var activityUserDetailBinding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.getParcelableExtra<UserDto>(USER_DETAILS)?.let { userDetails = it }

        initDataBinding()
    }

    private fun initDataBinding() {
        activityUserDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        activityUserDetailBinding.user = userDetails
    }
}