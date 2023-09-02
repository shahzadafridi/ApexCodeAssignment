import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.apex.codeassesment.FakeData
import com.apex.codeassesment.MainCoroutineRule
import com.apex.codeassesment.data.remote.mapper.UserMapper
import com.apex.codeassesment.data.repository.UserRepository
import com.apex.codeassesment.ui.main.MainViewModel
import com.apex.codeassesment.util.DataState
import com.apex.codeassesment.util.UiState
import com.apex.codeassesment.util.error.BaseException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get: Rule
    var coroutinesRule = MainCoroutineRule()

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var userRepository: UserRepository

    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(userRepository)
    }

    @Test
    fun test_get_user_data_with_force_update_false() = runTest {
        val forceUpdate = false
        val userResponseDTO = FakeData.userResponseDTO
        val users = UserMapper.toUser(userResponseDTO)
        val user = users.firstOrNull()

        coEvery { userRepository.getUser() } returns DataState.Success(userResponseDTO)

        viewModel.getUser(forceUpdate)

        coroutinesRule.dispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.user.value

        assertTrue(uiState is UiState.Success)
        assertTrue((uiState as UiState.Success).data == user)
        verify(atMost = 0, atLeast = 0){ userRepository.saveUser(userResponseDTO) }
    }

    @Test
    fun test_get_user_data_with_force_update_true() = runTest {
        val forceUpdate = true
        val userResponseDTO = FakeData.userResponseDTO
        val users = UserMapper.toUser(userResponseDTO)
        val user = users.firstOrNull()

        coEvery { userRepository.getUser() } returns DataState.Success(userResponseDTO)

        coEvery { userRepository.saveUser(userResponseDTO) } returns Unit

        viewModel.getUser(forceUpdate)

        coroutinesRule.dispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.user.value

        assertTrue(uiState is UiState.Success)
        assertTrue((uiState as UiState.Success).data == user)
        verify { userRepository.saveUser(userResponseDTO) }
    }

    @Test
    fun test_get_users_with_result_3() = runTest {
        val result = 3
        val userResponseDTO = FakeData.userResponseDTO
        val users = UserMapper.toUser(userResponseDTO)

        coEvery { userRepository.getUsers(result) } returns DataState.Success(userResponseDTO)

        viewModel.getUsers(result)

        coroutinesRule.dispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.users.value

        assertTrue(uiState is UiState.Success)
        assertTrue((uiState as UiState.Success).data == users)
        assertTrue((uiState as UiState.Success).data.size == result)
    }

    @Test
    fun test_get_users_with_exception() = runTest {
        val result = 3

        coEvery { userRepository.getUsers(result) } returns DataState.Error(BaseException(BaseException.Kind.UNEXPECTED,"UNEXPECTED"))

        viewModel.getUsers(result)

        coroutinesRule.dispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.users.value

        assertTrue(uiState is UiState.Error)
        assertTrue((uiState as UiState.Error).message == "UNEXPECTED")
    }

}