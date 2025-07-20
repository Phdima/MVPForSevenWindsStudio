package com.example.mvpforsevenwindsstudio.feature_order.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpforsevenwindsstudio.core.shared.CartRepository
import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuItemDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    val cartItems = cartRepository.getItemsFlow()


    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
        }
    }

    fun updateCartQuantity(item: MenuItemDomain, quantity: Int) {
        viewModelScope.launch {
            cartRepository.addOrUpdateItem(item, quantity)
        }
    }
}