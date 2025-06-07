package com.hse.hseproject.presentation.ui

import android.app.StatusBarManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColorInt
import com.hse.hseproject.presentation.navigation.MainNavScreen
import com.hse.hseproject.presentation.theme.HSEprojectTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {


            HSEprojectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    MainNavScreen()
                }
            }
//            }
//            val color = Color(0xFF14FF24) // Создаем объект Color
//            val colorInt = color.toColorInt() // Преобразуем в Int
            SystemBarStyle.light(
                Color(0xFF14ff24).toArgb(),
                Color(0xFF14ff24).toArgb(),
            )
            SystemBarStyle.dark(
                Color(0xFF14ff24).toArgb(),
            )
        }
    }
}

