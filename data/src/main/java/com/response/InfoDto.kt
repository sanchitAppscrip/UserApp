package com.response

import com.google.gson.annotations.SerializedName

data class InfoDto(
    @SerializedName("seed")
    val seed: String? = null,

    @SerializedName("results")
    val results: Int? = null,

    @SerializedName("page")
    val page: Int? = null
)
