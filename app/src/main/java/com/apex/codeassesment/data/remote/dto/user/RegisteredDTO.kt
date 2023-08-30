package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class RegisteredDTO(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)