package com.example.mvpforsevenwindsstudio.core.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavButton(
    isValidate: Boolean,
    navController: NavController,
    destination: String,
    modifier: Modifier,
    text: String
) {


    Button(
        onClick = {
            if (isValidate) {
                navController.navigate(destination)
            }
        },
        modifier = modifier
            .height(48.dp),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text)
    }

}