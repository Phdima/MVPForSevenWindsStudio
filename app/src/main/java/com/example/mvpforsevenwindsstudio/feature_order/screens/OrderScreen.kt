package com.example.mvpforsevenwindsstudio.feature_order.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvpforsevenwindsstudio.core.ui.components.NavButton
import com.example.mvpforsevenwindsstudio.feature_order.viewmodel.OrderViewModel
import com.example.mvpforsevenwindsstudio.feature_order.components.OrderItemsList

@Composable
fun OrderScreen(
    navController: NavController,
    viewModel: OrderViewModel = hiltViewModel(),
) {
    val cartItems by viewModel.cartItems.collectAsState(initial = emptyList())

    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        OrderItemsList(
            items = cartItems,
            modifier = Modifier.padding(),
            onQuantityUpdate = { item, quantity ->
                viewModel.updateCartQuantity(item, quantity)
            },
        )
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .height(90.dp)
                .offset(y = 60.dp)
                .padding(horizontal = 8.dp),
            text =  "  Время ожидания заказа\n" +
                    "               15 минут!\n" +
                    "Спасибо, что выбрали нас!",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 24.sp
        )

        NavButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .align(Alignment.BottomCenter),
            navController = navController,
            destination = "login",
            text = "Оплатить",
            isValidate = false
        )
    }
}