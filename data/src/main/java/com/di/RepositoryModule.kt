package com.di

import com.repo.UserRepo
import com.repo.UserRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGameRepository(userRepo: UserRepoImpl): UserRepo = userRepo

//    @Provides
//    @Singleton
//    fun provideGameHandler(): GameHandler {
//        return GameHandler()
//    }
}