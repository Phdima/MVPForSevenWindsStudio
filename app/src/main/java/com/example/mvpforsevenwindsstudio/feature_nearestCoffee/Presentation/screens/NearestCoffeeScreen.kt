package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mvpforsevenwindsstudio.core.ui.components.NavButton
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.components.LocationsList
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.viewModel.LocationsViewModel

@Composable
fun NearestCoffeeScreen(
    navController: NavController,
    viewModel: LocationsViewModel = hiltViewModel(),
) {
    val locations by viewModel.locations.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LocationsList(locations = locations)

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