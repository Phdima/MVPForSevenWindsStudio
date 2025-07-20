package com.example.mvpforsevenwindsstudio.feature_menu.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuItemDomain
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.Presentation.viewModel.LocationWithDistance

@Composable
fun CoffeeGrid(
    items: List<MenuItemDomain>,
    quantityMap: Map<Int, Int>,
    onQuantityUpdate: (MenuItemDomain, Int) -> Unit,
    modifier: Modifier = Modifier,
    onItemClick: (MenuItemDomain) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { item ->
            CoffeeItem(
                item = item,
                currentQuantity = quantityMap[item.id] ?: 0,
                onItemClick = { onItemClick(item) },
                onQuantityUpdate = { newQuantity ->
                    onQuantityUpdate(item, newQuantity)
                }
            )
        }
    }
}