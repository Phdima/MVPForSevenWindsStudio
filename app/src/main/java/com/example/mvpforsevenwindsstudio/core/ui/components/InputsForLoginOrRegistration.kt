package com.example.mvpforsevenwindsstudio.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mvpforsevenwindsstudio.core.navigation.NavRoute
import com.example.mvpforsevenwindsstudio.feature_registration.presentation.viewmodel.RegisterViewModel

@Composable
fun InputsForLoginOrRegistration(
    modifier: Modifier,
    navController: NavController,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordAgainChanged: (String) -> Unit,
    viewModelState: RegisterViewModel.RegisterState,
) {

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    Column(
        modifier = modifier
    ) {

        Text(text = "e-mail", fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary)

        TextField(

            value = viewModelState.email,
            onValueChange = { onEmailChanged(it) },
            placeholder = {
                Text(
                    "example@example.ru",
                    fontSize = 14.sp,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
            ),
            singleLine = true,
            modifier = Modifier
                .padding(bottom = 25.dp)
                .clip(RoundedCornerShape(50.dp))
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(50.dp)
                )

        )


        Text(text = "Пароль", fontSize = 16.sp, color = MaterialTheme.colorScheme.secondary)

        TextField(
            value = viewModelState.password,
            onValueChange = { onPasswordChanged(it) },
            placeholder = {
                Text(
                    "******",
                    fontSize = 14.sp,
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp,
            ),
            singleLine = true,
            modifier = Modifier
                .padding(bottom = 30.dp)
                .clip(RoundedCornerShape(50.dp))
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(50.dp)
                )
        )

        if (currentRoute == NavRoute.Registration.route) {
            Text(
                text = "Повторите пароль",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary
            )

            TextField(
                value = viewModelState.passwordAgain,
                onValueChange = { onPasswordAgainChanged(it) },
                placeholder = {
                    Text(
                        "******",
                        fontSize = 14.sp,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                    focusedTextColor = MaterialTheme.colorScheme.secondary,
                    unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 14.sp,
                ),
                singleLine = true,
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(50.dp)
                    )
            )
        }

    }
}