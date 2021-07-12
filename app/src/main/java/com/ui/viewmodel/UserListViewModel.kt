package com.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.error.AppError
import com.extensions.toApiFailure
import com.model.Resource
import com.model.Response
import com.remote.UserApi
import com.repo.UserRepoImpl
import com.response.ApiResponse
import com.response.UserResponse
import com.usecases.UserUseCase
import com.usecases.UserUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepo: UserRepoImpl,
    private val userUseCase: UserUseCaseImpl
) : ViewModel() {
    private val disposables = CompositeDisposable()

    private val userObserver: MutableLiveData<Resource<UserResponse>> =
        MutableLiveData<Resource<UserResponse>>()

    private var pageLoading = false

    fun isValidForPaging(): Boolean {
        return !pageLoading
    }

    fun getUsersObserver(): LiveData<Resource<UserResponse>> = userObserver

    fun getUsers(page: Int? = null, results: Int? = null, seed: String? = null) {
        pageLoading = true
        disposables.add(userUseCase.getUser(page, results, seed)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                userObserver.value = Resource.loading()
            }
            .subscribe({ response ->
                Log.d("Viewmodel", "Response Success")
                userObserver.value = Resource.success(response)
                pageLoading = false
            }) { throwable ->
                Log.d("Viewmodel", "Response Error")
                userObserver.value = Resource.error(throwable.toApiFailure())
                pageLoading = false
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}