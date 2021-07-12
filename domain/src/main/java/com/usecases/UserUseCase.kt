package com.usecases

import com.response.UserResponse
import io.reactivex.rxjava3.core.Single


interface UserUseCase {
    fun getUser(page: Int? = null, results: Int? = null, seed: String? = null)
            : Single<UserResponse>
}