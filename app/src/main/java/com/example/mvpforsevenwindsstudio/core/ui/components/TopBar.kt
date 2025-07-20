package com.example.mvpforsevenwindsstudio.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.mvpforsevenwindsstudio.core.navigation.NavRoute
import com.example.mvpforsevenwindsstudio.feature_login.LoginScreen
import com.example.mvpforsevenwindsstudio.feature_menu.presentation.screens.MenuScreen
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.screens.NearestCoffeeScreen
import com.example.mvpforsevenwindsstudio.feature_order.screens.OrderScreen
import com.example.mvpforsevenwindsstudio.feature_registration.presentation.screen.RegistrationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    val title = when (currentBackStack?.destination?.route?.substringBefore('/')){
        NavRoute.Login.route -> "Вход"
        NavRoute.Registration.route -> "Регистрация"
        NavRoute.NearestCoffee.route -> "Ближайшие кофейни"
        NavRoute.Map.route -> "Карта"
        NavRoute.Menu.route -> "Меню"
        NavRoute.Order.route -> "Ваш заказ"
        else -> {
            ""
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = -10.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                Modifier
                    .background(color = MaterialTheme.colorScheme.onBackground)
                    .border(
                        width = 1.dp,
                        brush = SolidColor(Color.Gray),
                        shape = RoundedCornerShape(1)
                    ),
                navigationIcon = {
                    if (currentRoute !in listOf(
                            NavRoute.Login.route,
                            NavRoute.Registration.route
                        )
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                Icons.Outlined.KeyboardArrowLeft,
                                "Назад",
                                tint = MaterialTheme.colorScheme.secondary
                            )

                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoute.Registration.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoute.Login.route) { LoginScreen(navController) }
            composable(NavRoute.Registration.route) { RegistrationScreen(navController) }
            composable(NavRoute.NearestCoffee.route) { NearestCoffeeScreen(navController) }
            composable(
                NavRoute.Menu.PATTERN,
                arguments = listOf(navArgument("locationId") { type = NavType.IntType })
            ) { backStackEntry ->
                val locationId = backStackEntry.arguments?.getInt("locationId") ?: 0
                MenuScreen(navController = navController, locationId = locationId)
            }
            composable("order") {
                OrderScreen(navController)
            }
        }
    }
}