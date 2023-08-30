package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class TimezoneDTO(
    @SerializedName("description")
    val description: String,
    @SerializedName("offset")
    val offset: String
)