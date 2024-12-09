package com.example.lend.di

import com.example.lend.onboarding.repository.LoginRepoImplementation
import com.example.lend.onboarding.repository.LoginRepoInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideLoginRepo() : LoginRepoInterface {
        return LoginRepoImplementation()
    }

}