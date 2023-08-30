package com.apex.codeassesment.data.remote

import com.apex.codeassesment.data.remote.dto.user.UserDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserAPI {

    @GET
    suspend fun getRandomUser(): UserDTO

    @GET
    suspend fun getRandomUsers(
        @Query("results") result: Int
    ): List<UserDTO>

}


