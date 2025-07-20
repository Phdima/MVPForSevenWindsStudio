package com.example.mvpforsevenwindsstudio.feature_menu.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvpforsevenwindsstudio.core.ui.components.NavButton
import com.example.mvpforsevenwindsstudio.feature_menu.presentation.components.CoffeeGrid
import com.example.mvpforsevenwindsstudio.feature_menu.presentation.viewmodel.MenuState
import com.example.mvpforsevenwindsstudio.feature_menu.presentation.viewmodel.MenuViewModel

@Composable
fun MenuScreen(
    navController: NavController,
    locationId: Int,
    viewModel: MenuViewModel = hiltViewModel(),
) {
    Log.d("MenuScreen", "Открытие экрана меню для locationId: $locationId")
    val state by viewModel.state.collectAsState()
    val cartItems by viewModel.cartItems.collectAsState(initial = emptyList())

    val quantityMap by remember(cartItems) {
        derivedStateOf {
            Log.d("MenuScreen", "Recalculating quantity map")
            cartItems.associate { it.product.id to it.quantity }
        }
    }

    Log.d("MenuScreen", "Текущее состояние: $state")

    LaunchedEffect(locationId) {
        Log.d("MenuScreen", "Запуск загрузки меню")
        viewModel.loadMenu(locationId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val currentState = state) {
            is MenuState.Success -> {
                Log.d(
                    "MenuScreen",
                    "Отображение успешного состояния с ${currentState.menu.size} элементами"
                )
                CoffeeGrid(
                    items = currentState.menu,
                    quantityMap = quantityMap,
                    onQuantityUpdate = { item, quantity ->
                        viewModel.updateCartQuantity(item, quantity)
                    },
                    modifier = Modifier.fillMaxSize(),
                    onItemClick = { item ->

                        navController.navigate("itemDetail/${item.id}")
                    }
                )
            }

            else -> {}


        }

        NavButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .align(Alignment.BottomCenter),
            navController = navController,
            destination = "order",
            text = "Перейти к оплате",
            isValidate = cartItems.isNotEmpty()
        )
    }
}