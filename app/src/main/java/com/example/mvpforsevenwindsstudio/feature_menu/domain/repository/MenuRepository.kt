package com.example.mvpforsevenwindsstudio.feature_menu.domain.repository

import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuItemDomain

interface MenuRepository {
    suspend fun getMenu(locationId: Int): Result<List<MenuItemDomain>>
}