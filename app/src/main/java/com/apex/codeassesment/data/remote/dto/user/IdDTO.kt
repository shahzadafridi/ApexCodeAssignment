package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class IdDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: Any?
)