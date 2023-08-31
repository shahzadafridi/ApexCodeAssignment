package com.apex.codeassesment.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apex.codeassesment.data.remote.mapper.UserMapper
import com.apex.codeassesment.data.repository.UserRepository
import com.apex.codeassesment.model.user.User
import com.apex.codeassesment.util.DataState
import com.apex.codeassesment.util.UiState
import com.google.gson.Gson
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){

    private val _user = MutableLiveData<UiState<User?>>()
    val user: LiveData<UiState<User?>> = _user

    private val _users = MutableLiveData<UiState<List<User>>>()
    val users: LiveData<UiState<List<User>>> = _users

    private var _randomUser: User? = null

    fun getUser() = _randomUser
    fun getUser(forceUpdate: Boolean) {
        _user.value = UiState.Loading
        viewModelScope.launch {
            when(val userDto = userRepository.getUser()) {
                is DataState.Success -> {
                    val user = UserMapper.toUser(userDto.data)
                    if (forceUpdate) {
                       userRepository.saveUser(userDto.data)
                    }
                    _randomUser = user.firstOrNull()
                    _user.value = UiState.Success(_randomUser)
                }
                is DataState.Error -> {
                    _user.value = UiState.Error(userDto.exception.message)
                }
            }
        }
    }

    fun getUsers(results: Int = 10) {
        _users.value = UiState.Loading
        viewModelScope.launch {
            when(val userDto = userRepository.getUsers(results = results)) {
                is DataState.Success -> {
                    val users = UserMapper.toUser(userDto.data)
                    _users.value = UiState.Success(users)
                }
                is DataState.Error -> {
                    _users.value = UiState.Error(userDto.exception.message)
                }
            }
        }
    }

}