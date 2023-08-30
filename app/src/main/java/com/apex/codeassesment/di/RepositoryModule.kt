package com.apex.codeassesment.di

import com.apex.codeassesment.data.UserRepositoryImp
import com.apex.codeassesment.data.local.localdatasource
import com.apex.codeassesment.data.remote.RemoteDataSource
import com.apex.codeassesment.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        localDataSource: localdatasource,
        remoteDataSource: RemoteDataSource
    ): UserRepository {
        return UserRepositoryImp(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }
}