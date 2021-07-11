package com.repo

import android.util.Log
import com.remote.UserApi
import com.response.ApiResponse
import com.response.UserResponse
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    val userApi: UserApi
) : UserRepo {
    override fun getUser(page: Int?, results: Int?, seed: String?): Single<UserResponse> {
        Log.d("Repo", " Call initiate")
//            val response =  userApi.getUser()

//        Log.d("Repo"," Call completed")

        return userApi.getUser(page, results, seed)

//        return Single.create<UserResponse>{
//            userApi.getUser()
//        }.subscribeOn(Schedulers.io())
    }
}