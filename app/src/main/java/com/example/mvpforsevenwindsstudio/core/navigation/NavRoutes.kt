package com.example.mvpforsevenwindsstudio.core.navigation

sealed class NavRoute(val route: String) {
    object Registration : NavRoute("registration")
    object Login : NavRoute("login")
    object NearestCoffee : NavRoute("nearestCoffee")
    object Map : NavRoute("map")
    object Menu : NavRoute("menu")
    object Order : NavRoute("order")
}