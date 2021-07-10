package com.remote

import com.model.Resource
import com.response.ApiResponse
import com.response.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("api")
    fun getUser(): Single<ApiResponse>
}