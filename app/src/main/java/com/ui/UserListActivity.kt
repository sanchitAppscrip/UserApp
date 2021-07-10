package com.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.model.Status
import com.ui.viewmodel.UserListViewModel
import com.test.userapp.R
import com.test.userapp.databinding.ActivityUserListBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : AppCompatActivity() {
    private lateinit var viewModel: UserListViewModel
    private lateinit var activityUserListBinding: ActivityUserListBinding
    private lateinit var adapter: GroupAdapter<GroupieViewHolder<ActivityUserListBinding>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        viewModel.getUsers()

        setObserver()

        adapter.setOnItemClickListener { item, view ->
            if (item is UserItem) {
                val intent = item.userDto?.let { UserDetailActivity.getStartIntent(it, this) }
                startActivity(intent)
            }

        }
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

    private fun initDataBinding() {
        activityUserListBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)

        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)

        activityUserListBinding.gameViewModel = viewModel
        activityUserListBinding.lifecycleOwner = this

        adapter = GroupAdapter()
        activityUserListBinding.rvUser.adapter = adapter

    }
}