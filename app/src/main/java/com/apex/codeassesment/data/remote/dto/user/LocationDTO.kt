package com.apex.codeassesment.data.remote.dto.user


import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("city")
    val city: String,
    @SerializedName("coordinates")
    val coordinatesDTO: CoordinatesDTO,
    @SerializedName("country")
    val country: String,
    @SerializedName("postcode")
    val postcode: Int,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val streetDto: StreetDTO,
    @SerializedName("timezone")
    val timezoneDto: TimezoneDTO
)