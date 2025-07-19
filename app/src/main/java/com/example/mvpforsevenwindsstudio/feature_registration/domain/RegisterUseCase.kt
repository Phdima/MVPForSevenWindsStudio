package com.example.mvpforsevenwindsstudio.feature_registration.domain

import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.dto.AuthResponse
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        login: String,
        password: String,
    ): Result<AuthResponse> = repository.register(login, password)
}