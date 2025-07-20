package com.example.mvpforsevenwindsstudio.feature_order.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mvpforsevenwindsstudio.core.shared.CartItem
import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuItemDomain

@Composable
fun OrderItemsList(
    items: List<CartItem>,
    modifier: Modifier = Modifier,
    onQuantityUpdate: (MenuItemDomain, Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items, key = { it.product.id }) { item ->
            OrderItemCard( item = item,
                onQuantityUpdate = { newQuantity ->
                    onQuantityUpdate(item.product, newQuantity)
                })
        }
    }
}