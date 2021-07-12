package com.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
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
        activityUserDetailBinding.ivProfile.apply {
            Glide.with(this)
                .load(userDetails.image?.large)
                .placeholder(R.color.color_divider)
                .into(this)
        }

        activityUserDetailBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    private fun initDataBinding() {
        activityUserDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        activityUserDetailBinding.user = userDetails
    }
}