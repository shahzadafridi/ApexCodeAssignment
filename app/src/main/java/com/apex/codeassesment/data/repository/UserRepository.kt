package com.apex.codeassesment.data.repository

import com.apex.codeassesment.data.model.User

interface UserRepository {

    fun getSavedUser(): User

    fun getUser(forceUpdate: Boolean): User

    fun getUsers(): List<User>

}