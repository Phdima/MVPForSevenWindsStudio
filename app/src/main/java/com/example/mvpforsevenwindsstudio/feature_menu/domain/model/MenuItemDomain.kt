package com.example.mvpforsevenwindsstudio.feature_menu.domain.model


import com.example.mvpforsevenwindsstudio.feature_menu.data.remote.dto.MenuItemDto
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote.dto.LocationDto
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationDomain
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationMapper

data class MenuItemDomain(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val price: Double
)
object MenuMapper {

    fun toDomain(dto: MenuItemDto): MenuItemDomain {
        return MenuItemDomain(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.imageUrl,
            price = dto.price
        )
    }


    fun toDomainList(dtoList: List<MenuItemDto>): List<MenuItemDomain> {
        return dtoList.map { toDomain(it) }
    }
}