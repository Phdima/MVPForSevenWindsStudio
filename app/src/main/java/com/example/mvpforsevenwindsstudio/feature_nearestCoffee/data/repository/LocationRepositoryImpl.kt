package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.repository

import com.example.mvpforsevenwindsstudio.core.SessionAccess.SessionManager
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote.LocationsApi
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.repository.LocationRepository
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.Location
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationMapper
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val api: LocationsApi,
    private val sessionManager: SessionManager
) : LocationRepository {

    override suspend fun getLocations(): Result<List<Location>> {
        return try {
            val token = sessionManager.getToken()
            val response = api.getLocations("Bearer $token")

            if (response.isSuccessful) {
                val dtoList = response.body() ?: emptyList()
                val domainList = LocationMapper.toDomainList(dtoList)
                Result.success(domainList)
            } else {
                val errorMsg = "Error: ${response.code()} - ${response.errorBody()?.string()}"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Network error: ${e.message}"))
        }
    }
}