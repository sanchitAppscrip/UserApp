package com.remote

import com.model.Resource
import com.response.ApiResponse
import com.response.UserResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("api")
    fun getUser(
        @Query("page") page: Int? = null,
        @Query("results") results: Int? = null,
        @Query("seed") seed: String? = null,
    ): Single<UserResponse>
}