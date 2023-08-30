package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class UserResponseDTO(
    @SerializedName("info")
    val infoDto: InfoDTO,
    @SerializedName("results")
    val userDto: List<UserDTO>
)