package com.apex.codeassesment.data

import com.apex.codeassesment.data.local.LocalDataSource
import com.apex.codeassesment.data.remote.RemoteDataSource
import com.apex.codeassesment.data.remote.dto.user.UserResponseDTO
import com.apex.codeassesment.data.repository.UserRepository
import com.apex.codeassesment.util.DataState
import javax.inject.Inject

// TODO (2 points) : Add tests
class UserRepositoryImp @Inject constructor(
  private val localDataSource: LocalDataSource,
  private val remoteDataSource: RemoteDataSource
): UserRepository {

  override fun saveUser(userResponseDTO: UserResponseDTO) {
    localDataSource.saveUser(userResponseDTO)
  }
  override fun getSavedUser() = localDataSource.loadUser()

  override suspend fun getUser(): DataState<UserResponseDTO>  {
      return remoteDataSource.loadUser()
  }
  override suspend fun getUsers(results: Int): DataState<UserResponseDTO> {
    return remoteDataSource.loadUsers(results = results)
  }

}
