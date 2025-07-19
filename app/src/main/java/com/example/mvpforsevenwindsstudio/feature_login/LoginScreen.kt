package com.example.mvpforsevenwindsstudio.feature_login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvpforsevenwindsstudio.core.ui.components.InputsForLoginOrRegistration
import com.example.mvpforsevenwindsstudio.core.ui.components.NavButton

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {

        Column {
            InputsForLoginOrRegistration(modifier = Modifier.padding(top = 190.dp, start = 16.dp, end = 16.dp), navController = navController)
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
