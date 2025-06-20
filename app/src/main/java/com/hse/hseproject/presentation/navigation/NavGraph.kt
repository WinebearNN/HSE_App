package com.hse.hseproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hse.hseproject.presentation.ui.authentication.AuthenticationScreen
import com.hse.hseproject.presentation.ui.event.EventScreen
import com.hse.hseproject.presentation.ui.logIn.LogInScreen
import com.hse.hseproject.presentation.ui.ticket.TicketScreen
import com.hse.hseproject.presentation.ui.home.HomeScreen
import com.hse.hseproject.presentation.ui.profile.ProfileScreen

//import com.hse.projectcurrency.presentation.ui.calculator.CalculatorScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "authentication"
    ) {
        composable(NavigationScreen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(NavigationScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(NavigationScreen.Ticket.route) {
            TicketScreen(navController)
        }
        composable(NavigationScreen.LogIn.route) {
            LogInScreen(navController)
        }
        composable(
            route = NavigationScreen.Authentication.route
        ) {
            AuthenticationScreen(navController)
        }
        composable(
            route = NavigationScreen.Event.route,
            arguments = listOf(navArgument("eventId") { type = NavType.StringType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId") ?: ""
            EventScreen( eventId = eventId)
        }


//        composable(NavigationScreen.Chats.route) { ChatScreen() }
//        composable(NavigationScreen.Settings.route) { SettingsScreen() }
    }
}