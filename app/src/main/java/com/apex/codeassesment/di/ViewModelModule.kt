package com.apex.codeassesment.di

import androidx.lifecycle.ViewModel
import com.apex.codeassesment.common.ViewModelKey
import com.apex.codeassesment.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}