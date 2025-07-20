package com.example.mvpforsevenwindsstudio.feature_login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvpforsevenwindsstudio.core.ui.components.InputsForLoginOrRegistration
import com.example.mvpforsevenwindsstudio.core.ui.components.NavButton
import com.example.mvpforsevenwindsstudio.feature_registration.presentation.viewmodel.RegisterViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {

        Column {
            InputsForLoginOrRegistration(
                modifier = Modifier.padding(top = 190.dp, start = 16.dp, end = 16.dp),
                navController = navController,
                onEmailChanged = { email -> viewModel.updateEmail(email) },
                onPasswordChanged = { password -> viewModel.updatePassword(password) },
                onPasswordAgainChanged = { password -> viewModel.updatePasswordAgain(password) },
                viewModelState = state
            )
            NavButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                navController = navController,
                destination = "nearestCoffee",
                text = "Войти",
                isValidate = true
            )
        }

    }
}
