package com.sangmee.mypractice.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun providesContext(application: Application): Context {
        return application
    }
}
