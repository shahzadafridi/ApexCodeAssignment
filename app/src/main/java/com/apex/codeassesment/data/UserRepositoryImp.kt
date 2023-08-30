package com.apex.codeassesment.data

import com.apex.codeassesment.data.local.LocalDataSource
import com.apex.codeassesment.data.remote.RemoteDataSource
import com.apex.codeassesment.data.remote.mapper.UserMapper
import com.apex.codeassesment.data.repository.UserRepository
import com.apex.codeassesment.model.user.User
import javax.inject.Inject

// TODO (2 points) : Add tests
class UserRepositoryImp @Inject constructor(
  private val localDataSource: LocalDataSource,
  private val remoteDataSource: RemoteDataSource
): UserRepository {

  override fun getSavedUser() = localDataSource.loadUser()

  override suspend fun getUser(forceUpdate: Boolean): User {
    if (forceUpdate) {
      val userDto = remoteDataSource.loadUser()
      val user = UserMapper.toUser(userDto)
      localDataSource.saveUser(user)
    }
    return getSavedUser()
  }
  override suspend fun getUsers(results: Int) = remoteDataSource.loadUsers(results = results)

}
