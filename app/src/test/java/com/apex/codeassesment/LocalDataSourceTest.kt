package com.apex.codeassesment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apex.codeassesment.data.local.LocalDataSource
import com.apex.codeassesment.data.local.PreferencesManager
import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class LocalDataSourceTest {

    lateinit var localDataSource: LocalDataSource

    @MockK
    lateinit var preferencesManager: PreferencesManager

    @MockK
    lateinit var gson: Gson


    @Before
    fun setup(){
        MockKAnnotations.init(this)
        localDataSource = LocalDataSource(preferencesManager,gson)
    }

    @Test
    fun test_save_user_to_local_source()  {
        val userResponseDTO = mockk<UserResponseDTO>()

        coEvery { preferencesManager.saveUser(anyString()) } returns Unit

        coEvery { gson.toJson(userResponseDTO) } returns FakeData.userResponseJson

        coEvery { preferencesManager.saveUser(FakeData.userResponseJson) } returns Unit

        localDataSource.saveUser(userResponseDTO)

        coVerify { gson.toJson(userResponseDTO) }
        coVerify { preferencesManager.saveUser(FakeData.userResponseJson) }

    }

    @Test
    fun test_get_user_from_local_source() {

        val userResponseDto = FakeData.userResponseDTO

        coEvery { preferencesManager.loadUser() } returns FakeData.userResponseJson
        coEvery { gson.fromJson(FakeData.userResponseJson, UserResponseDTO::class.java) } returns userResponseDto

        val result = localDataSource.loadUser()

        coVerify { preferencesManager.loadUser() }
        coVerify { gson.fromJson(FakeData.userResponseJson, UserResponseDTO::class.java) }
        assert(result == userResponseDto)
    }



}