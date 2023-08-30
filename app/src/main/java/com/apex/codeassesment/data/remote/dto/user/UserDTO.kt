package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("info")
    val infoDto: InfoDTO,
    @SerializedName("results")
    val resultDto: List<ResultDTO>
)