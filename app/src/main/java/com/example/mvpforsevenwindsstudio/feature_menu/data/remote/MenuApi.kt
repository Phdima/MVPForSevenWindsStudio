package com.example.mvpforsevenwindsstudio.feature_menu.data.remote


import com.example.mvpforsevenwindsstudio.feature_menu.data.remote.dto.MenuItemDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MenuApi {
    @GET("location/{id}/menu")
    suspend fun getMenu(
        @Path("id") locationId: Int,
        @Header("Authorization") token: String
    ):  Response<List<MenuItemDto>>
}