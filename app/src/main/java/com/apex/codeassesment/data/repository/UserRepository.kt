package com.apex.codeassesment.data.repository

import com.apex.codeassesment.data.model.user.User

interface UserRepository {

    fun getSavedUser(): User

    suspend fun getUser(forceUpdate: Boolean): User

    suspend fun getUsers(results: Int): List<User>

}