package com.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class UserDto(
    @SerializedName("gender")
    val gender: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("nat")
    val nat: String? = null,

    @SerializedName("name")
    val name: NameDto? = null,

    @SerializedName("picture")
    val image: ImageDto? = null,

    @SerializedName("dob")
    val dob: DobDto? = null,

    @SerializedName("location")
    val location: LocationDto? = null
) : Parcelable
