package com.example.mvpforsevenwindsstudio.core.di

import com.example.mvpforsevenwindsstudio.core.shared.CartRepository
import com.example.mvpforsevenwindsstudio.core.shared.CartRepositoryImpl
import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.AuthApi
import com.example.mvpforsevenwindsstudio.feature_registration.data.repository.AuthRepositoryImpl
import com.example.mvpforsevenwindsstudio.feature_registration.domain.AuthRepository
import com.example.mvpforsevenwindsstudio.feature_registration.domain.RegisterUseCase
import com.example.mvpforsevenwindsstudio.core.shared.SessionManager
import com.example.mvpforsevenwindsstudio.feature_menu.data.remote.MenuApi
import com.example.mvpforsevenwindsstudio.feature_menu.data.repository.MenuRepositoryImpl
import com.example.mvpforsevenwindsstudio.feature_menu.domain.repository.MenuRepository
import com.example.mvpforsevenwindsstudio.feature_menu.domain.useCase.GetMenuUseCase
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote.LocationsApi
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.repository.LocationsRepositoryImpl
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.repository.LocationRepository
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.useCase.LocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideAuthRepository(api: AuthApi, sessionManager: SessionManager): AuthRepository =
        AuthRepositoryImpl(api, sessionManager)


    @Provides
    @ViewModelScoped
    fun provideRegisterUseCase(repository: AuthRepository): RegisterUseCase =
        RegisterUseCase(repository)



    @Provides
    @ViewModelScoped
    fun provideLocationRepository(api:LocationsApi, sessionManager: SessionManager ): LocationRepository =
        LocationsRepositoryImpl(api,sessionManager)


    @Provides
    @ViewModelScoped
    fun provideLocationUseCase(repository: LocationRepository): LocationUseCase =
        LocationUseCase(repository)


    @Provides
    @ViewModelScoped
    fun provideMenuRepository(api:MenuApi, sessionManager: SessionManager ): MenuRepository =
        MenuRepositoryImpl(api,sessionManager)

    @Provides
    @ViewModelScoped
    fun provideMenuUseCase(repository: MenuRepository): GetMenuUseCase =
        GetMenuUseCase(repository)

}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository {
        return CartRepositoryImpl()
    }
}
