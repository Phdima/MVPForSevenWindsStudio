package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.repository

import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.Location

interface LocationRepository {
    suspend fun getLocations(): Result<List<Location>>
}