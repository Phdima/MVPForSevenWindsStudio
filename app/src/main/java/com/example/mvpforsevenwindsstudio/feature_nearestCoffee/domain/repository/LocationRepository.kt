package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.repository

import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationDomain

interface LocationRepository {
    suspend fun getLocations(): Result<List<LocationDomain>>
}