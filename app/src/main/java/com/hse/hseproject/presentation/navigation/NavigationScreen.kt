package com.hse.hseproject.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

open class NavigationScreen(val route: String, val title: String, val icon: ImageVector) {
    object LogIn : NavigationScreen("logIn", "Регистрация", Icons.Default.Person)
    object Profile : NavigationScreen("profile", "Профиль", Icons.Default.Person)
    object Ticket : NavigationScreen("ticket", "Мероприятия", Icons.Default.Favorite)
    object Home : NavigationScreen("home", "Главная", Icons.Default.Home)
    object Authentication : NavigationScreen("authentication", "Аутентификация", Icons.Default.Warning)
    object Event : NavigationScreen("event","Мероприятие", Icons.Default.Warning)

}