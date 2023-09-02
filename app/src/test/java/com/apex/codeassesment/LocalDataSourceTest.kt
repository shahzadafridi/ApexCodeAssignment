package com.apex.codeassesment

import com.apex.codeassesment.data.local.LocalDataSource
import com.apex.codeassesment.data.local.PreferencesManager
import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
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

        every { preferencesManager.saveUser(anyString()) } returns Unit

        every { gson.toJson(userResponseDTO) } returns FakeData.userResponseJson

        every { preferencesManager.saveUser(FakeData.userResponseJson) } returns Unit

        localDataSource.saveUser(userResponseDTO)

        verify { gson.toJson(userResponseDTO) }
        verify { preferencesManager.saveUser(FakeData.userResponseJson) }

    }

    @Test
    fun test_get_user_from_local_source() {

        val userResponseDto = FakeData.userResponseDTO

        every { preferencesManager.loadUser() } returns FakeData.userResponseJson
        every { gson.fromJson(FakeData.userResponseJson, UserResponseDTO::class.java) } returns userResponseDto

        val result = localDataSource.loadUser()

        verify { preferencesManager.loadUser() }
        verify { gson.fromJson(FakeData.userResponseJson, UserResponseDTO::class.java) }
        assert(result == userResponseDto)
    }



}