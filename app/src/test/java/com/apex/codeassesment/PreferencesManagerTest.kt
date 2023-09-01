package com.apex.codeassesment

import android.content.SharedPreferences
import com.apex.codeassesment.data.local.PreferencesManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
class PreferencesManagerTest {

    @MockK
    lateinit var sharedPreferences: SharedPreferences

    lateinit var preferencesManager: PreferencesManager

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        preferencesManager = PreferencesManager(sharedPreferences)
    }

    @Test
    fun test_save_user_to_shared_pref()  {
        val key = "saved-user"
        val value = FakeData.userResponseJson

        val editor = mockk<SharedPreferences.Editor>(relaxed = true)
        coEvery { sharedPreferences.edit() } returns editor
        coEvery { editor.putString(key, value) } returns editor
        coEvery { editor.apply() } returns Unit

        preferencesManager.saveUser(value)

        coVerify {
            editor.putString(key, value)
            editor.apply()
        }
    }

    @Test
    fun test_get_user_from_shared_pref()  {
        val key = "saved-user"
        val expected = FakeData.userResponseJson

        coEvery { sharedPreferences.getString(key,null) } returns expected

        val result = preferencesManager.loadUser()

        Assert.assertTrue(expected == result)
    }

    @Test
    fun test_save_and_get_user_from_shared_pref()  {
        val key = "saved-user"
        val expected = FakeData.userResponseJson

        val editor = mockk<SharedPreferences.Editor>(relaxed = true)
        coEvery { sharedPreferences.getString(key,null) } returns expected
        coEvery { sharedPreferences.edit() } returns editor
        coEvery { editor.putString(key, expected) } returns editor
        coEvery { editor.apply() } returns Unit

        preferencesManager.saveUser(expected)
        coVerify {
            editor.putString(key, expected)
            editor.apply()
        }

        val result = preferencesManager.loadUser()
        Assert.assertTrue(expected == result)
    }
}