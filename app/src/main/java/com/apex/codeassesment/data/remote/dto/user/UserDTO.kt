package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("cell")
    val cell: String,
    @SerializedName("dob")
    val dobDTO: DobDTO,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val idDTO: IdDTO,
    @SerializedName("location")
    val locationDto: LocationDTO,
    @SerializedName("login")
    val loginDto: LoginDTO,
    @SerializedName("name")
    val nameDto: NameDTO,
    @SerializedName("nat")
    val nat: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("picture")
    val pictureDto: PictureDTO,
    @SerializedName("registered")
    val registeredDto: RegisteredDTO
)