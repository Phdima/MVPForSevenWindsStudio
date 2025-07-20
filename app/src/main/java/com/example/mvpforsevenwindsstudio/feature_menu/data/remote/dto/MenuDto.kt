package com.example.mvpforsevenwindsstudio.feature_menu.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MenuItemDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("price") val price: Double
)

