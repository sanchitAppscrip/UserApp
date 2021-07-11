package com.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.error.AppError
import com.model.Resource
import com.model.Response
import com.remote.UserApi
import com.repo.UserRepoImpl
import com.response.ApiResponse
import com.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepo: UserRepoImpl,
    val userApi: UserApi
) : ViewModel() {
    private val disposables = CompositeDisposable()

    private var page = 0

    val userObserver: MutableLiveData<Resource<UserResponse>> =
        MutableLiveData<Resource<UserResponse>>()

    fun getUsersObserver(): LiveData<Resource<UserResponse>> = userObserver

    fun getUsers(page: Int? = null, results: Int? = null, seed: String? = null) {
        disposables.add(userRepo.getUser(page, results, seed)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({ gameStatus ->
                Log.d("Viewmodel", "Response Success")
                Log.d("Viewmodel", " value ${gameStatus.info}")
                Log.d("Viewmodel", " value ${gameStatus?.results?.size}")
                userObserver.value = Resource.success(gameStatus)
            }) { throwable ->

                Log.d("Viewmodel", "Response Error")
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}