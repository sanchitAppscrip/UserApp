package com.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NameDto(
    @SerializedName("first")
    val first: String? = null,

    @SerializedName("last")
    val last: String? = null,

    @SerializedName("title")
    val title: String? = null
) : Parcelable
