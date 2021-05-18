package com.sangmee.mypractice.di.main

import androidx.lifecycle.ViewModel
import com.sangmee.mypractice.MainViewModel
import com.sangmee.mypractice.di.scope.ActivityScope
import com.sangmee.mypractice.models.dataSource.PostRemoteDataSource
import com.sangmee.mypractice.models.dataSource.PostRemoteDataSourceImpl
import com.sangmee.mypractice.models.repository.PostRepository
import com.sangmee.mypractice.models.repository.PostRepositoryImpl
import com.sangmee.mypractice.viewmodel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class MainModule {

    @ActivityScope
    @Provides
    fun providePostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository {
        return postRepositoryImpl
    }

    @ActivityScope
    @Provides
    fun providePostRemoteDataSource(postRemoteDataSourceImpl: PostRemoteDataSourceImpl): PostRemoteDataSource {
        return postRemoteDataSourceImpl
    }

    @ActivityScope
    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(postRepository: PostRepository): ViewModel {
        return MainViewModel(postRepository)
    }
}
