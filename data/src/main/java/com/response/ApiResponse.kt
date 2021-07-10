package com.response

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val message: String?,
    val error: String?,
    val json: UserResponse? = null
)