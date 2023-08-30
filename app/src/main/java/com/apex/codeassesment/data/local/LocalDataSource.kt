package com.apex.codeassesment.data.local

import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import com.google.gson.Gson
import javax.inject.Inject

// TODO (3 points): Convert to Kotlin
// TODO (2 point): Add tests
// TODO (1 point): Use the correct naming conventions.
// TODO (3 points): Inject all dependencies instead of instantiating them.
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
