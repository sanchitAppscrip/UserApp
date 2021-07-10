package com.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DobDto(
    @SerializedName("date")
    val date: String? = null,

    @SerializedName("age")
    val age: Int? = null
) : Parcelable
