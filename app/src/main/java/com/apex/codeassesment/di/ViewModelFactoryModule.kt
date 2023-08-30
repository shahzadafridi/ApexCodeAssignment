package com.apex.codeassesment.di

import androidx.lifecycle.ViewModelProvider
import com.apex.codeassesment.common.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}