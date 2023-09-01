package com.apex.codeassesment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import com.apex.codeassesment.data.repository.UserRepository
import com.apex.codeassesment.model.user.User
import com.apex.codeassesment.ui.main.MainActivity
import com.apex.codeassesment.ui.main.MainViewModel
import com.apex.codeassesment.util.DataState
import com.apex.codeassesment.util.UiState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as mockWhen


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    lateinit var scenario: ActivityScenario<MainActivity>

    @MockK
    lateinit var viewModel: MainViewModel
    @MockK
    lateinit var userRepository: UserRepository
    @MockK
    lateinit var userObserver: Observer<UiState<User?>>
    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        userRepository = mockk()
        viewModel = MainViewModel(userRepository)
        userObserver = mockk()
        viewModel.user.observeForever(userObserver)
        scenario = launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun check_initial_state_of_screen() {
        onView(withId(R.id.main_refresh_button)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.main_user_list_button)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.main_name)).check(matches(ViewMatchers.withText("")))
        onView(withId(R.id.main_email)).check(matches(ViewMatchers.withText("")))
        onView(withId(R.id.main_user_list))
            .check(matches(hasChildCount(0)))
    }

    @Test
    fun press_refres_button_shows_user_details() = runTest {
        onView(withId(R.id.main_name)).check(matches(ViewMatchers.withText("")))
        onView(withId(R.id.main_email)).check(matches(ViewMatchers.withText("")))
        onView(withId(R.id.main_refresh_button)).perform(ViewActions.click())
        val slot = slot<UiState<User?>>()
        val user = mockk<UiState<User?>>()
        every { userObserver.onChanged(capture(slot)) } answers {

        }
        val userResponseDTO = mockk<UserResponseDTO>()
        coEvery { userRepository.getUser() } returns DataState.Success(userResponseDTO)

        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertEquals(viewModel.user.getOrAwaitValue(), user)
    }
}