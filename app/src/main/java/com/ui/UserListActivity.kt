package com.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.extensions.cannotScrollBottom
import com.model.Status
import com.response.UserDto
import com.test.userapp.R
import com.test.userapp.databinding.ActivityUserListBinding
import com.ui.items.LoadingItem
import com.ui.items.UserItem
import com.ui.viewmodel.UserListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.databinding.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : AppCompatActivity(), UserItem.Callback {
    companion object {
        private const val FLIPPER_CHILD_LIST = 0
        private const val FLIPPER_CHILD_LOADING = 1
    }

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
        initialise()
        setListeners()
        setObserver()
    }

    private fun initialise() {
        seed = UserToken.seed
        viewModel.getUsers(page = pageNo, results = pageResults, seed = seed)
    }

    private fun setListeners() {
        activityUserListBinding.rvUser.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.cannotScrollBottom() && viewModel.isValidForPaging()) {
                    viewModel.getUsers(pageNo, pageResults, seed)

                }
            }
        })

    }

    private fun setObserver() {
        viewModel.getUsersObserver().observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    if (isFirstpage)
                        activityUserListBinding.viewFlipper.displayedChild = FLIPPER_CHILD_LOADING
                    else
                        adapter.add(loadingItem)
                }

                Status.SUCCESS -> {
                    activityUserListBinding.viewFlipper.displayedChild = FLIPPER_CHILD_LIST
                    resource.data?.results?.let {
                        addUsersToAdapter(it)
                    }
                }

                Status.ERROR -> {
                    activityUserListBinding.viewFlipper.displayedChild = FLIPPER_CHILD_LIST
                }
            }
        })
    }

    private fun initDataBinding() {
        activityUserListBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)

        viewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        activityUserListBinding.lifecycleOwner = this

        adapter = GroupAdapter()
        activityUserListBinding.rvUser.adapter = adapter
    }

    private fun addUsersToAdapter(list: List<UserDto>) {
        if (isFirstpage) {
            adapter.clear()
        } else {
            pageNo += pageNo
            adapter.remove(loadingItem)
        }
        list.forEach { user ->
            val item = UserItem(user, this)
            adapter.add(item)
        }
        isFirstpage = false
    }

    override fun onUserItemClickListener(userDto: UserDto) {
        val intent = UserDetailActivity.getStartIntent(userDto, this)
        startActivity(intent)
    }

}