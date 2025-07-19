package com.example.mvpforsevenwindsstudio.feature_registration.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    var isValidate by remember { mutableStateOf(true) }/*пока не понял почему не проходит валидацию, поэтому оставил true */

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
           isValidate = true
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
                ), navController = navController
            )
            NavButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                navController = navController,
                destination = "login",
                text = "Регистрация",
                isValidate = isValidate
            )
        }

    }
}
