package com.apex.codeassesment.data

import com.apex.codeassesment.data.local.localdatasource
import com.apex.codeassesment.data.model.User
import com.apex.codeassesment.data.remote.RemoteDataSource
import com.apex.codeassesment.data.repository.UserRepository
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject

// TODO (2 points) : Add tests
class UserRepositoryImp @Inject constructor(
  private val localDataSource: localdatasource,
  private val remoteDataSource: RemoteDataSource
): UserRepository {

  private val savedUser = AtomicReference(User())

  override fun getSavedUser() = localDataSource.loadUser()

  override fun getUser(forceUpdate: Boolean): User {
    if (forceUpdate) {
      val user = remoteDataSource.LoadUser()
      localDataSource.saveUser(user)
      savedUser.set(user)
    }
    return savedUser.get()
  }

  override fun getUsers() = remoteDataSource.loadUsers()
}
