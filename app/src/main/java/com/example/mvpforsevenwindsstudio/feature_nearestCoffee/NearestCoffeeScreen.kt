package com.example.mvpforsevenwindsstudio.feature_nearestCoffee

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvpforsevenwindsstudio.core.ui.components.NavButton

@Composable
fun NearestCoffeeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .align(Alignment.BottomCenter),
            navController = navController,
            destination = "login",
            text = "На карте",
            isValidate = true
        )
    }
}