package com.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    @field:SerializedName("results")
    val results: List<UserDto>? = null,

    @field:SerializedName("info")
    val info: String? = null

) : Parcelable
