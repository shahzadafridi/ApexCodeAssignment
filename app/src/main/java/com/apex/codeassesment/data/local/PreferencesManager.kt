package com.apex.codeassesment.data.local

import android.content.Context
import android.content.SharedPreferences
import com.apex.codeassesment.ui.main.MainActivity
import javax.inject.Inject

class PreferencesManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

  fun saveUser(user: String) {
    sharedPreferences.edit().putString("saved-user", user).apply()
  }

  fun loadUser(): String? {
     return sharedPreferences.getString("saved-user", null)
  }

}
