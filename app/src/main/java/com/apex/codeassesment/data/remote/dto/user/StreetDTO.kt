package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class StreetDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: Int
)