package com.apex.codeassesment.data.remote

import com.apex.codeassesment.util.error.HttpExceptionHandler
import javax.inject.Inject

// TODO (2 points): Add tests
class RemoteDataSource @Inject constructor(
  private val randomUserAPI: RandomUserAPI,
  private val exceptionHandler: HttpExceptionHandler
) {

  // TODO (5 points)[Done]: Load data from endpoint https://randomuser.me/api
  suspend fun loadUser() = exceptionHandler.wrap {
    randomUserAPI.getRandomUser()
  }

  // TODO (3 points)[Done]: Load data from endpoint https://randomuser.me/api?results=10
  // TODO (Optional Bonus: 3 points)[Done]: Handle succes and failure from endpoints
  suspend fun loadUsers(results: Int) = exceptionHandler.wrap {
    randomUserAPI.getRandomUsers(results)
  }
}
