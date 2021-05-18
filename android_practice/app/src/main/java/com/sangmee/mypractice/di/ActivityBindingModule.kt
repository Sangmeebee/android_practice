package com.sangmee.mypractice.di

import com.sangmee.mypractice.MainActivity
import com.sangmee.mypractice.di.main.MainModule
import com.sangmee.mypractice.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
