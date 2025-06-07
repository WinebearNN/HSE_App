package com.hse.hseproject.presentation.ui.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hse.hseproject.presentation.animation.StartAnimation
import com.hse.hseproject.presentation.viewmodel.auth.AuthResult
import com.hse.hseproject.presentation.viewmodel.auth.AuthViewModel
import kotlinx.coroutines.delay
import kotlin.math.max


@Composable
fun AuthenticationScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val authResult by viewModel.authResult.collectAsState()
    var showLoading by remember { mutableStateOf(true) }
    var loadingStartTime by remember { mutableLongStateOf(0L) }

    val colorScheme = MaterialTheme.colorScheme
    val isDarkTheme = isSystemInDarkTheme()

    LaunchedEffect(Unit) {
        viewModel.auth()
        loadingStartTime = System.currentTimeMillis()
    }

    LaunchedEffect(authResult) {
        when (authResult) {
            is AuthResult.GuestSuccess,
            is AuthResult.StudentSuccess -> {
                val elapsed = System.currentTimeMillis() - loadingStartTime
                val remaining = max(0, 3000 - elapsed)

                if (remaining > 0) {
                    delay(remaining)
                }

                navController.navigate("profile") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
                showLoading = false
            }

            is AuthResult.Error -> {
                val elapsed = System.currentTimeMillis() - loadingStartTime
                val remaining = max(0, 3000 - elapsed)

                if (remaining > 0) {
                    delay(remaining)
                }

                navController.navigate("logIn") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
                showLoading = false
            }

            AuthResult.Loading -> {
                // Продолжаем показывать загрузку
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        if (showLoading) {
            // Ваша анимация загрузки (пример с Lottie)
            StartAnimation(isDarkTheme)
        }
    }
}
