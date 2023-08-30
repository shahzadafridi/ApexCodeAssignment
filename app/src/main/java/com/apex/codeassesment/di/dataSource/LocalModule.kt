package com.apex.codeassesment.di.dataSource

import com.apex.codeassesment.data.local.PreferencesManager
import com.apex.codeassesment.data.local.LocalDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
object LocalModule {

    @Provides
    fun provideLocalDataSource(preferencesManager: PreferencesManager, gson: Gson): LocalDataSource{
        return LocalDataSource(
            preferencesManager = preferencesManager,
            gson = gson
        )
    }

}