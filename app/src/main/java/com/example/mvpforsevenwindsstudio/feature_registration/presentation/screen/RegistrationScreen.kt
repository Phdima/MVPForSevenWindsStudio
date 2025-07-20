package com.example.mvpforsevenwindsstudio.feature_registration.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvpforsevenwindsstudio.core.navigation.NavRoute
import com.example.mvpforsevenwindsstudio.core.ui.components.InputsForLoginOrRegistration
import com.example.mvpforsevenwindsstudio.core.ui.components.NavButton
import com.example.mvpforsevenwindsstudio.feature_registration.presentation.viewmodel.RegisterViewModel

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val navigationState by viewModel.navigateTo.collectAsState()


    LaunchedEffect(navigationState) {
        navigationState?.let { destination ->
            navController.navigate(destination) {
                popUpTo("registration") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {

        Column {
            InputsForLoginOrRegistration(
                modifier = Modifier.padding(
                    top = 190.dp,
                    start = 16.dp,
                    end = 16.dp
                ),     navController = navController,
                onEmailChanged = { email -> viewModel.updateEmail(email) },
                onPasswordChanged = { password -> viewModel.updatePassword(password) },
                onPasswordAgainChanged = { password -> viewModel.updatePasswordAgain(password) },
                viewModelState = state
            )
            Button(
                onClick = {
                    viewModel.register()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                enabled = !state.isLoading && state.isFormValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                )
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(color = Color.Black)
                } else {
                    Text("Регистрация")
                }
            }
            state.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

    }
}
