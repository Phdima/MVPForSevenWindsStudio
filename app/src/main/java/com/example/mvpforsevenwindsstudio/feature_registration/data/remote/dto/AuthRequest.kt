package com.example.mvpforsevenwindsstudio.feature_registration.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("login")
    val login: String,

    @SerializedName("password")
    val password: String,

)