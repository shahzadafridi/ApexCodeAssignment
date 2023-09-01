package com.apex.codeassesment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apex.codeassesment.data.UserRepositoryImp
import com.apex.codeassesment.data.local.LocalDataSource
import com.apex.codeassesment.data.remote.RemoteDataSource
import com.apex.codeassesment.data.remote.dto.user.InfoDTO
import com.apex.codeassesment.data.remote.dto.user.UserDTO
import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import com.apex.codeassesment.data.repository.UserRepository
import com.apex.codeassesment.util.DataState
import com.apex.codeassesment.util.error.BaseException
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserRepositoryImpTest {


    @get: Rule
    var coroutinesRule = MainCoroutineRule()

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var localDataSource: LocalDataSource
    @MockK
    lateinit var remoteDataSource: RemoteDataSource
    @MockK
    lateinit var userRepository: UserRepository

    @Before
    fun setup(){
        localDataSource = mockk()
        remoteDataSource = mockk()
        userRepository = UserRepositoryImp(localDataSource,remoteDataSource)
    }

    @Test
    fun test_save_user_to_local_source() {
        val userResponseDTO = mockk<UserResponseDTO>()

        coEvery { localDataSource.saveUser(userResponseDTO) } returns Unit

        coEvery { userRepository.saveUser(userResponseDTO) } returns Unit

        userRepository.saveUser(userResponseDTO)

        verify { localDataSource.saveUser(userResponseDTO) }
        verify { userRepository.saveUser(userResponseDTO) }
    }

    @Test
    fun test_load_saved_user_from_local_source() {
        val userResponseDTO = mockk<UserResponseDTO>()

        coEvery { localDataSource.loadUser() } returns userResponseDTO
        coEvery { userRepository.getSavedUser() } returns userResponseDTO

        val result = userRepository.getSavedUser()

        verify { localDataSource.loadUser() }
        verify { userRepository.getSavedUser() }

        assertEquals(userResponseDTO,result)
    }

    @Test
    fun test_save_and_load_user_from_local_source() {
        val userResponseDTO = mockk<UserResponseDTO>()

        coEvery { localDataSource.saveUser(userResponseDTO) } returns Unit
        coEvery { userRepository.saveUser(userResponseDTO) } returns Unit

        coEvery { localDataSource.loadUser() } returns userResponseDTO
        coEvery { userRepository.getSavedUser() } returns userResponseDTO

        userRepository.saveUser(userResponseDTO)
        val result = userRepository.getSavedUser()

        verify { localDataSource.saveUser(userResponseDTO) }
        verify { userRepository.saveUser(userResponseDTO) }
        verify { localDataSource.loadUser() }
        verify { userRepository.getSavedUser() }

        assertEquals(userResponseDTO,result)
    }

    @Test
    fun test_get_user_from_remote_source() = runTest {
        val userResponseDTO = mockk<DataState<UserResponseDTO>>()

        coEvery { remoteDataSource.loadUser() } returns userResponseDTO
        coEvery { userRepository.getUser() } returns userResponseDTO

        val result = userRepository.getUser()

        coroutinesRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(userResponseDTO,result)
    }

    @Test
    fun test_get_users_with_results_10_from_remote_source() = runTest {
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

        coEvery { remoteDataSource.loadUsers(results = 10) } returns DataState.Success(userResponseDTO)
        coEvery { userRepository.getUsers(results = 10) } returns DataState.Success(userResponseDTO)

        val result = userRepository.getUsers(results = 10)

        coroutinesRule.dispatcher.scheduler.advanceUntilIdle()

        assertTrue((result as DataState.Success).data.userDto.size == 10)
    }

    @Test
    fun test_get_users_with_failure_from_remote_source() = runTest {
        coEvery { remoteDataSource.loadUsers(results = 10) } returns DataState.Error(BaseException(BaseException.Kind.UNEXPECTED,"Unexpected error"))
        coEvery { userRepository.getUsers(results = 10) } returns DataState.Error(BaseException(BaseException.Kind.UNEXPECTED,"Unexpected error"))

        val result = userRepository.getUsers(results = 10)

        coroutinesRule.dispatcher.scheduler.advanceUntilIdle()

        assertTrue((result as DataState.Error).exception.message.equals("Unexpected error"))
    }
}