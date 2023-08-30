package com.apex.codeassesment.data.repository

import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import com.apex.codeassesment.util.DataState


interface UserRepository {

    fun getSavedUser(): UserResponseDTO

    fun saveUser(userResponseDTO: UserResponseDTO)

    suspend fun getUser(): DataState<UserResponseDTO>

    suspend fun getUsers(results: Int): DataState<UserResponseDTO>

}