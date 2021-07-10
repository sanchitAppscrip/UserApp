package com.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageDto(
    @SerializedName("large")
    val large: String? = null,

    @SerializedName("medium")
    val medium: String? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null
) : Parcelable
