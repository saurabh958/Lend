package com.example.lend.navigation.app_navigation

enum class Screen {
    DASHBOARD,
    LOGIN,
    SIGNUP
}


sealed class AppNavigation(val route:String) {
    data object Home : AppNavigation(Screen.DASHBOARD.name)
    data object Login : AppNavigation(Screen.LOGIN.name)
    data object Signup : AppNavigation(Screen.SIGNUP.name)
}