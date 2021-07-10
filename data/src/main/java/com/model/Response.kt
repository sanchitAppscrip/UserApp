package com.model

import androidx.annotation.NonNull
import com.response.UserResponse


data class Response(
    val status: Status,
    var data: UserResponse? = null,
    val error: Throwable? = null
) {
    companion object {
        fun loading(): Response {
            return Response(Status.LOADING, null, null)
        }

        fun success(@NonNull data: UserResponse?): Response {
            return Response(Status.SUCCESS, data, null)
        }

        fun error(@NonNull error: Throwable?): Response {
            return Response(Status.ERROR, null, error)
        }
    }

}