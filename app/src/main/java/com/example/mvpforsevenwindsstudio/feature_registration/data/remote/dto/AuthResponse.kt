package com.example.mvpforsevenwindsstudio.feature_registration.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("tokenLifeTime")
    val tokenLifeTime: Long
)