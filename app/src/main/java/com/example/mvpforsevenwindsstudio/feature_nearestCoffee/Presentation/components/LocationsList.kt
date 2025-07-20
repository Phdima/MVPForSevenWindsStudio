package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.Location

@Composable
fun LocationsList(locations: List<Location>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(locations) { _, location ->
            CoffeeShopItem(location = location)
        }
    }
}