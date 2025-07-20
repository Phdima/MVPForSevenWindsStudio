package com.example.mvpforsevenwindsstudio.feature_registration.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpforsevenwindsstudio.feature_registration.domain.RegisterUseCase
import com.example.mvpforsevenwindsstudio.core.SessionAccess.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val sessionManager: SessionManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState = _uiState.asStateFlow()

    private val _navigateTo = MutableStateFlow<String?>(null)
    val navigateTo: StateFlow<String?> = _navigateTo


    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
        validateForm()
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
        validateForm()
    }


    fun updatePasswordAgain(passwordAgain: String) {
        _uiState.value = _uiState.value.copy(passwordAgain = passwordAgain)
        validateForm()
    }

    private fun validateForm() {
        val state = _uiState.value
        val isEmailValid = state.email.isNotBlank()
        val isPasswordValid = state.password.isNotEmpty()
        val doPasswordsMatch = state.password == state.passwordAgain

        val passwordError = when {
            state.passwordAgain.isNotEmpty() && !doPasswordsMatch -> "Пароли не совпадают"
            else -> null
        }

        _uiState.value = state.copy(
            isFormValid = isEmailValid && isPasswordValid && doPasswordsMatch,
            passwordError = passwordError
        )
    }


    fun register() {
        if (!_uiState.value.isFormValid) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val result = registerUseCase(_uiState.value.email, _uiState.value.password)

            if (result.isSuccess) {
                _uiState.value = RegisterState(
                    isSuccess = true,
                    isLoading = false,
                )
                sessionManager.saveToken(result.getOrNull()?.token ?: "")

                _navigateTo.value = "login"

            } else {
                _uiState.value = RegisterState(
                    errorMessage = result.exceptionOrNull()?.message ?: "Registration failed"

                )
            }

            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

    data class RegisterState(
        val email: String = "",
        val password: String = "",
        val passwordAgain: String = "",
        val isFormValid: Boolean = false,
        val isLoading: Boolean = false,
        val isSuccess: Boolean = false,
        val passwordError: String? = null,
        val errorMessage: String? = null,
    )

}