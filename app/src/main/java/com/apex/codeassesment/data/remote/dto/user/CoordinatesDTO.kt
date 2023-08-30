package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class CoordinatesDTO(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)