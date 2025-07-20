package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.repository

import android.util.Log
import com.example.mvpforsevenwindsstudio.core.shared.SessionManager
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote.LocationsApi
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.repository.LocationRepository
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationDomain
import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.domain.model.LocationMapper
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val api: LocationsApi,
    private val sessionManager: SessionManager,
) : LocationRepository {

    override suspend fun getLocations(): Result<List<LocationDomain>> {
        return try {
            val token = sessionManager.getToken()
            val response = api.getLocations("Bearer $token")

            if (response.isSuccessful) {
                val dtoList = response.body() ?: emptyList()
                val domainList = LocationMapper.toDomainList(dtoList)

                Log.d("LocationsRepo", "Received ${domainList.size} locations")
                domainList.forEach {
                    Log.d("LocationData", "${it.name}: ${it.latitude}, ${it.longitude}")
                }
                Result.success(domainList)

            } else {
                Log.e("LocationsRepo", "API error: ${response.code()}")
                val errorMsg = "Error: ${response.code()} - ${response.errorBody()?.string()}"
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Log.e("LocationsRepo", "Network error", e)
            Result.failure(Exception("Network error: ${e.message}"))
        }
    }
}