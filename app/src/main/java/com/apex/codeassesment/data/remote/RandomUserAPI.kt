package com.apex.codeassesment.data.remote

import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserAPI {

    @GET("/api")
    suspend fun getRandomUser(): UserResponseDTO

    @GET("/api")
    suspend fun getRandomUsers(
        @Query("results") result: Int
    ): UserResponseDTO

}


