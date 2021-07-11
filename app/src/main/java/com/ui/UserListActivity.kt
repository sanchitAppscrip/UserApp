package com.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.extensions.cannotScrollBottom
import com.model.Status
import com.response.UserDto
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
    private lateinit var seed: String
    private var isFirstpage = true
    private var pageResults = 10
    private var pageNo = 1
    private val loadingItem by lazy { LoadingItem() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        setListeners()
        seed = UserSeed.seed
        viewModel.getUsers(page = pageNo, results = pageResults, seed = seed)

        setObserver()

        adapter.setOnItemClickListener { item, view ->
            if (item is UserItem) {
                val intent = item.userDto?.let { UserDetailActivity.getStartIntent(it, this) }
                startActivity(intent)
            }

        }
    }

    private fun setListeners() {
        activityUserListBinding.rvUser.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.cannotScrollBottom()) {
                    adapter.add(loadingItem)
                    viewModel.getUsers(pageNo, pageResults, seed)

                }
            }
        })
    }

    private fun setObserver() {
        viewModel.getUsersObserver().observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                }

                Status.SUCCESS -> {
                    resource.data?.results?.let {
                        addUsersToAdapter(it)
                    }
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

    fun addUsersToAdapter(list: List<UserDto>) {
        if (isFirstpage) {
            adapter.clear()
        } else {
            pageNo += pageNo
            adapter.remove(loadingItem)
        }
        list.forEach { user ->
            val item = UserItem(user)
            adapter.add(item)
        }
        isFirstpage = false
    }
}