package com.repo

import com.response.ApiResponse
import com.response.UserResponse
import io.reactivex.rxjava3.core.Single

interface UserRepo {
    fun getUser(page: Int? = null, results: Int? = null, seed: String? = null): Single<UserResponse>
}