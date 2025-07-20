package com.example.mvpforsevenwindsstudio.feature_menu.domain.useCase

import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuItemDomain
import com.example.mvpforsevenwindsstudio.feature_menu.domain.repository.MenuRepository
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationDomain
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.repository.LocationRepository
import javax.inject.Inject

class GetMenuUseCase @Inject constructor(
    private val repository: MenuRepository,
) {
    suspend operator fun invoke(id : Int): Result<List<MenuItemDomain>> {
        return repository.getMenu(id)
    }
}