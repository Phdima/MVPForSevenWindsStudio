package com.example.mvpforsevenwindsstudio.core.SessionAccess

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val TOKEN_KEY = "auth_token"
    }

    fun saveToken(token: String) {
        context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            .edit()
            .putString(TOKEN_KEY, token)
            .apply()
    }

    fun getToken(): String? {
        return context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getString(TOKEN_KEY, null)
    }
}