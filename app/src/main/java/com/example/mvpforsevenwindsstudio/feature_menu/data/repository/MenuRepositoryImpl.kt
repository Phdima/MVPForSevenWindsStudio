package com.example.mvpforsevenwindsstudio.feature_menu.data.repository

import android.util.Log
import com.example.mvpforsevenwindsstudio.core.shared.SessionManager
import com.example.mvpforsevenwindsstudio.feature_menu.data.remote.MenuApi
import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuItemDomain
import com.example.mvpforsevenwindsstudio.feature_menu.domain.model.MenuMapper
import com.example.mvpforsevenwindsstudio.feature_menu.domain.repository.MenuRepository
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val api: MenuApi,
    private val sessionManager: SessionManager
) : MenuRepository {

    override suspend fun getMenu(locationId: Int): Result<List<MenuItemDomain>> {
        return try {
            Log.d("MenuRepository", "Запрос меню для locationId: $locationId")
            val token = sessionManager.getToken() ?: throw Exception("Токен не найден")
            Log.d("MenuRepository", "Используемый токен: ${token.take(10)}...")

            val response = api.getMenu(locationId, "Bearer $token")
            Log.d("MenuRepository", "Ответ сервера: ${response.code()} - ${response.message()}")

            if (response.isSuccessful) {
                val dtoList = response.body() ?: emptyList()
                Log.d("MenuRepository", "Получено ${dtoList.size} элементов в DTO")

                val menu = MenuMapper.toDomainList(dtoList)
                Log.d("MenuRepository", "Сконвертировано ${menu.size} элементов в Domain")
                Result.success(menu)
            } else {
                val errorBody = response.errorBody()?.string() ?: "Нет тела ошибки"
                Log.e("MenuRepository", "Ошибка API: ${response.code()} - $errorBody")
                Result.failure(Exception("Ошибка ${response.code()}: $errorBody"))
            }
        } catch (e: Exception) {
            Log.e("MenuRepository", "Исключение при загрузке меню", e)
            Result.failure(e)
        }
    }
}