package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class PictureDTO(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)