package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class InfoDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: Int,
    @SerializedName("seed")
    val seed: String,
    @SerializedName("version")
    val version: String
)