package com.apex.codeassesment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apex.codeassesment.data.remote.RandomUserAPI
import com.apex.codeassesment.data.remote.RemoteDataSource
import com.apex.codeassesment.data.remote.dto.user.InfoDTO
import com.apex.codeassesment.data.remote.dto.user.UserDTO
import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import com.apex.codeassesment.util.DataState
import com.apex.codeassesment.util.error.BaseException
import com.apex.codeassesment.util.error.HttpExceptionHandler
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coInvoke
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RemoteDataSourceTest {

    @get: Rule
    var coroutinesRule = MainCoroutineRule()

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var randomUserAPI: RandomUserAPI

    @MockK
    lateinit var exceptionHandler: HttpExceptionHandler

    @MockK
    lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup(){
        MockKAnnotations.init(this)
        remoteDataSource = RemoteDataSource(randomUserAPI,exceptionHandler)
    }

    @Test
    fun test_get_user_from_remote_source() = runTest {
        val userResponseDTO = mockk<UserResponseDTO>()
        val expected = DataState.Success(userResponseDTO)
        coEvery { randomUserAPI.getRandomUser() } returns userResponseDTO
        coEvery { exceptionHandler.wrap<UserResponseDTO>(captureLambda()) } coAnswers {
            DataState.Success(lambda<suspend () -> UserResponseDTO>().coInvoke())
        }
        val result = remoteDataSource.loadUser()
        coroutinesRule.dispatcher.scheduler.advanceUntilIdle()
        assertEquals(expected, result)
    }

    @Test
    fun test_get_users_from_remote_source() = runTest {
        val userDTO = mockk<UserDTO>()
        val results = arrayListOf<UserDTO>().apply {
            add(userDTO)
            add(userDTO)
            add(userDTO)
            add(userDTO)
            add(userDTO)
            add(userDTO)
            add(userDTO)
            add(userDTO)
            add(userDTO)
            add(userDTO)
        }
        val infoDTO = mockk<InfoDTO>()
        val userResponseDTO = UserResponseDTO(
            infoDto = infoDTO,
            userDto = results
        )
        val expected = DataState.Success(userResponseDTO)
        val numberOfUsers = 10
        coEvery { randomUserAPI.getRandomUsers(result = numberOfUsers) } returns userResponseDTO
        coEvery { exceptionHandler.wrap<UserResponseDTO>(captureLambda()) } coAnswers {
            DataState.Success(userResponseDTO)
        }
        val result = remoteDataSource.loadUser()
        assertEquals(expected, result)
        assertTrue((result as DataState.Success).data.userDto.size == 10)
    }

    @Test
    fun test_get_user_from_remote_source_and_throw_error_and_handle_exception() = runTest {
        val expectedException = BaseException(BaseException.Kind.UNEXPECTED,"API call failed")
        coEvery { randomUserAPI.getRandomUser() }.throws(expectedException)
        coEvery { exceptionHandler.wrap<UserResponseDTO>(captureLambda()) } coAnswers {
            DataState.Error(expectedException)
        }
        val result = remoteDataSource.loadUser()

        assertTrue((result as DataState.Error).exception.message.equals("API call failed"))
    }



}