package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote

import com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote.dto.LocationDto

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface LocationsApi {
    @GET("locations")
    suspend fun getLocations(
        @Header("Authorization") token: String? = null,
    ): Response<List<LocationDto>>
}