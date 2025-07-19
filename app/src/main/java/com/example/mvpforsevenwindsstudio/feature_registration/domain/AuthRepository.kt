package com.example.mvpforsevenwindsstudio.feature_registration.domain

import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.dto.AuthResponse

interface AuthRepository {
    suspend fun register(
        login: String,
        password: String,
    ): Result<AuthResponse>
}