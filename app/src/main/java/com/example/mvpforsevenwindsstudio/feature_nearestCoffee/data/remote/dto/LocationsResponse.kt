package com.example.mvpforsevenwindsstudio.feature_nearestCoffee.data.remote.dto

import com.google.gson.annotations.SerializedName




data class LocationDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("point")
    val point: GeoPointDto
)



data class GeoPointDto(
    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double
)