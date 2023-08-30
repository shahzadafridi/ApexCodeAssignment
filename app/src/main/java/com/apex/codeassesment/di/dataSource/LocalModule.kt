package com.apex.codeassesment.di.dataSource

import com.apex.codeassesment.data.local.PreferencesManager
import com.apex.codeassesment.data.local.localdatasource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LocalModule {

    @Provides
    fun provideLocalDataSource(preferencesManager: PreferencesManager): localdatasource = localdatasource(preferencesManager)

}