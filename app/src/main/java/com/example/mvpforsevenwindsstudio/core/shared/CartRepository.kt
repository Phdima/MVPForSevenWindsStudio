package com.example.mvpforsevenwindsstudio.core.shared

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuItemDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

data class CartItem(
    val product: MenuItemDomain,
    val quantity: Int
)

interface CartRepository {
    fun addItem(item: MenuItemDomain, quantity: Int)
    fun removeItem(itemId: Int)
    fun addOrUpdateItem(item: MenuItemDomain, quantity: Int)
    fun getItemsFlow(): Flow<List<CartItem>>
    fun getTotalPrice(): Double
    fun clearCart()
}



@Singleton
class CartRepositoryImpl @Inject constructor() : CartRepository {
    private val _items = mutableStateMapOf<Int, CartItem>()
    private val _itemsFlow = MutableStateFlow(_items.values.toList())

    private fun updateFlow() {
        val newList = _items.values.toList()
        Log.d("CartRepository", "Updating cart: ${newList.size} items")
        _itemsFlow.value = _items.values.toList()
    }

    override fun addItem(item: MenuItemDomain, quantity: Int) {
        _items[item.id] = _items[item.id]?.copy(
            quantity = _items[item.id]!!.quantity + quantity
        ) ?: CartItem(item, quantity)
        updateFlow()
    }

    override fun removeItem(itemId: Int) {
        _items.remove(itemId)
        updateFlow()
    }

    override fun addOrUpdateItem(item: MenuItemDomain, quantity: Int) {
        if (quantity > 0) {
            _items[item.id] = _items[item.id]?.copy(quantity = quantity) ?: CartItem(item, quantity)
            updateFlow()
        } else {
            _items.remove(item.id)
            updateFlow()
        }
    }

    override fun getItemsFlow(): Flow<List<CartItem>> = _itemsFlow

    override fun getTotalPrice(): Double {
        return _items.values.sumOf { it.product.price * it.quantity }
    }

    override fun clearCart() {
        _items.clear()
    }
}