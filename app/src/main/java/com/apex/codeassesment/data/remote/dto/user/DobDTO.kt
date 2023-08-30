package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class DobDTO(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)