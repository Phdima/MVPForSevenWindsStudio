package com.example.mvpforsevenwindsstudio.feature_registration.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpforsevenwindsstudio.feature_registration.domain.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun register(
        login: String,
        password: String,
    ) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            val result = registerUseCase(login, password)

            if (result.isSuccess) {
                _uiState.value = AuthUiState(
                    isSuccess = true,
                    token = result.getOrNull()?.token ?: ""
                )
            } else {
                _uiState.value = AuthUiState(
                    error = result.exceptionOrNull()?.message ?: "Registration failed"
                )
            }

            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    data class AuthUiState(
        val isLoading: Boolean = false,
        val isSuccess: Boolean = false,
        val token: String = "",
        val error: String? = null,
    )
}