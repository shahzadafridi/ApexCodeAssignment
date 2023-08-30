package com.apex.codeassesment.data.local

import com.apex.codeassesment.model.user.User
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

    fun loadUser(): User {
        val userJson = preferencesManager.loadUser()
        val user = gson.fromJson(userJson,User::class.java)
        return user
    }

    fun saveUser(user: User) {
        val userJson = gson.toJson(user)
        preferencesManager.saveUser(userJson)
    }
}
