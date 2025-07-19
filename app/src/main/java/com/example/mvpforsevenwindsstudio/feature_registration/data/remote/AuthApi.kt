package com.example.mvpforsevenwindsstudio.feature_registration.data.remote

import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.dto.AuthRequest
import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.dto.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun register(
        @Body request: AuthRequest
    ): Response<AuthResponse>
}