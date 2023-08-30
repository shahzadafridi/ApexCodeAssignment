package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class NameDTO(
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
    @SerializedName("title")
    val title: String
)