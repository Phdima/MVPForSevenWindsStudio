package com.example.mvpforsevenwindsstudio.core.di

import android.content.Context
import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.AuthApi
import com.example.mvpforsevenwindsstudio.core.shared.SessionManager
import com.example.mvpforsevenwindsstudio.feature_menu.data.remote.MenuApi
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote.LocationsApi
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.service.LocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://212.41.30.90:35005/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationsApi(retrofit: Retrofit): LocationsApi {
        return retrofit.create(LocationsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMenuApi(retrofit: Retrofit): MenuApi {
        return retrofit.create(MenuApi::class.java)
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideSessionManager(context: Context) : SessionManager = SessionManager(context)


    @Provides
    @Singleton
    fun provideLocationService(@ApplicationContext context: Context): LocationService {
        return LocationService(context)
    }
}