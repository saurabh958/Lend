package com.example.lend.di

import com.example.lend.onboarding.login.repository.LoginRepoImplementation
import com.example.lend.onboarding.login.repository.LoginRepoInterface
import com.example.lend.onboarding.signup.repository.SignUpRepoImp
import com.example.lend.onboarding.signup.repository.SignUpRepository
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

    @Provides
    fun provideSignupRepo() : SignUpRepository {
        return  SignUpRepoImp()
    }

}