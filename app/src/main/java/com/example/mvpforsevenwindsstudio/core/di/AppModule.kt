package com.example.mvpforsevenwindsstudio.core.di

import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.AuthApi
import com.example.mvpforsevenwindsstudio.feature_registration.data.repository.AuthRepositoryImpl
import com.example.mvpforsevenwindsstudio.feature_registration.domain.AuthRepository
import com.example.mvpforsevenwindsstudio.feature_registration.domain.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideAuthRepository(api: AuthApi): AuthRepository = AuthRepositoryImpl(api)


    @Provides
    @ViewModelScoped
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase = RegisterUseCase(repository)
}
