package com.apex.codeassesment.data.remote

import com.apex.codeassesment.util.error.HttpExceptionHandler
import javax.inject.Inject

// TODO (2 points): Add tests
class RemoteDataSource @Inject constructor(
  private val randomUserAPI: RandomUserAPI,
  private val exceptionHandler: HttpExceptionHandler
) {

  suspend fun loadUser() = exceptionHandler.wrap {
    randomUserAPI.getRandomUser()
  }

  suspend fun loadUsers(results: Int) = exceptionHandler.wrap {
    randomUserAPI.getRandomUsers(results)
  }
}
