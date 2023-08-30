package com.apex.codeassesment.di

import com.apex.codeassesment.data.UserRepositoryImp
import com.apex.codeassesment.data.local.LocalDataSource
import com.apex.codeassesment.data.remote.RemoteDataSource
import com.apex.codeassesment.data.repository.UserRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideUserRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): UserRepository {
        return UserRepositoryImp(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }
}