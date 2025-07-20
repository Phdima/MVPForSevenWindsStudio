package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model

import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote.dto.GeoPointDto
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote.dto.LocationDto

data class LocationDomain(
    val id: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double
)

object LocationMapper {

    fun toDomain(dto: LocationDto): LocationDomain {
        return LocationDomain(
            id = dto.id,
            name = dto.name,
            latitude = dto.point.latitude,
            longitude = dto.point.longitude
        )
    }

    fun toDomainList(dtoList: List<LocationDto>): List<LocationDomain> {
        return dtoList.map { toDomain(it) }
    }

    fun toDto(domain: LocationDomain): LocationDto {
        return LocationDto(
            id = domain.id,
            name = domain.name,
            point = GeoPointDto(
                latitude = domain.latitude,
                longitude = domain.longitude
            )
        )
    }
}