package com.example.mvpforsevenwindsstudio.feature_registration.data.repository

import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.AuthApi
import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.dto.AuthRequest
import com.example.mvpforsevenwindsstudio.feature_registration.data.remote.dto.AuthResponse
import com.example.mvpforsevenwindsstudio.feature_registration.domain.AuthRepository
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun register(
        login: String,
        password: String,
    ): Result<AuthResponse> {
        return safeApiCall {
            authApi.register(
                AuthRequest(
                    login = login,
                    password = password
                )
            )
        }
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorCode = response.code()
                val errorMsg = when (errorCode) {
                    400 -> "Ошибка в запросе"
                    406 -> "Такой логин уже используется "
                    else -> "Ошибка сервера: $errorCode"
                }
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Сетевая ошибка: ${e.localizedMessage}"))
        }
    }
}