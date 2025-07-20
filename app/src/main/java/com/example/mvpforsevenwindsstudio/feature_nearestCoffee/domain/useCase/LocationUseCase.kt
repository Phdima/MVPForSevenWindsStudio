package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.useCase

import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationDomain
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.repository.LocationRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val repository: LocationRepository,
) {
    suspend operator fun invoke(): Result<List<LocationDomain>> {
        return repository.getLocations()
    }
}