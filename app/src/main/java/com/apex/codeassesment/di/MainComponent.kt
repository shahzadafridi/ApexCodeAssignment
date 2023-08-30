package com.apex.codeassesment.di

import android.content.Context
import com.apex.codeassesment.di.dataSource.LocalModule
import com.apex.codeassesment.di.dataSource.NetworkModule
import com.apex.codeassesment.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        MainModule::class,
        RepositoryModule::class,
        LocalModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class
    ]
)
interface MainComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): MainComponent
    }

    interface Injector {
        val mainComponent: MainComponent
    }

    fun inject(mainActivity: MainActivity)
}