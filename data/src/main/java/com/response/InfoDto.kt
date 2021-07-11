package com.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoDto(
    @SerializedName("seed")
    val seed: String? = null,

    @SerializedName("results")
    val results: Int? = null,

    @SerializedName("page")
    val page: Int? = null
) : Parcelable
