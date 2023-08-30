package com.apex.codeassesment.data.remote

import retrofit2.http.GET
interface RandomUserAPI {

    @GET
    suspend fun getRandomUser(): String

    @GET
    suspend fun getRandomUsers(): String

}


