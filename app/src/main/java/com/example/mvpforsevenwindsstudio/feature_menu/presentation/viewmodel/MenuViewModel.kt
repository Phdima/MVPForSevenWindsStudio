package com.example.mvpforsevenwindsstudio.feature_menu.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvpforsevenwindsstudio.core.shared.CartItem
import com.example.mvpforsevenwindsstudio.core.shared.CartRepository
import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuItemDomain
import com.example.mvpforsevenwindsstudio.feature_menu.domain.useCase.GetMenuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getMenu: GetMenuUseCase,
    private val cartRepository: CartRepository,
) : ViewModel() {


    private val _state = MutableStateFlow<MenuState>(MenuState.Loading)
    val state: StateFlow<MenuState> = _state

    val cartItems: Flow<List<CartItem>> = cartRepository.getItemsFlow()
        .onEach { items ->
            Log.d("MenuViewModel", "Cart updated: ${items.size} items")
        }



    fun loadMenu(locationId: Int) {
        Log.d("MenuViewModel", "Загрузка меню для locationId: $locationId")
        viewModelScope.launch {
            val result = getMenu(id = locationId)

            when {
                result.isSuccess -> {
                    val menu = result.getOrNull() ?: emptyList()
                    Log.d("MenuViewModel", "Успешно загружено ${menu.size} элементов меню")
                    _state.value = MenuState.Success(menu)
                }

                else -> {
                    val exception = result.exceptionOrNull()
                    val errorMsg = exception?.message ?: "Неизвестная ошибка"
                    Log.e("MenuViewModel", "Ошибка загрузки меню: $errorMsg", exception)
                    _state.value = MenuState.Error(errorMsg)
                }
            }
        }
    }

    fun updateCartQuantity(item: MenuItemDomain, quantity: Int) {
        viewModelScope.launch {
            cartRepository.addOrUpdateItem(item, quantity)
        }
    }

}

sealed class MenuState {
    object Loading : MenuState()
    data class Success(val menu: List<MenuItemDomain>) : MenuState()
    data class Error(val message: String) : MenuState()
}