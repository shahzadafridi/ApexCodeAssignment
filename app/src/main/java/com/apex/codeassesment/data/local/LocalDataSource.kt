package com.apex.codeassesment.data.local

import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import com.google.gson.Gson
import javax.inject.Inject

// TODO (2 point): Add tests
class LocalDataSource @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val gson: Gson
) {

    fun loadUser(): UserResponseDTO {
        val userJson = preferencesManager.loadUser()
        val userResponseDto = gson.fromJson(userJson,UserResponseDTO::class.java)
        return userResponseDto
    }

    fun saveUser(userResponseDto: UserResponseDTO) {
        val userJson = gson.toJson(userResponseDto)
        preferencesManager.saveUser(userJson)
    }
}
