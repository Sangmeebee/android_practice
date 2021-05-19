package com.sangmee.mypractice.di

import androidx.lifecycle.ViewModelProvider
import com.sangmee.mypractice.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
