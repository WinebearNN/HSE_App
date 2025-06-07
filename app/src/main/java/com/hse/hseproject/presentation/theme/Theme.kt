package com.hse.hseproject.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


import androidx.compose.ui.graphics.Color
import com.hse.hseproject.presentation.theme.LightGray

private val LightColorScheme = lightColorScheme(
    // Основные цвета
    primary = White,
    onPrimary = Black,
    secondary = Color(0xFF0F2954),  // Синий
    onSecondary = White,

    // Третичные/дополнительные цвета
    tertiary = TextGray,
    onTertiary = Black,

    // Контейнеры и поверхности
    primaryContainer = White,  // Светло-серый
    onPrimaryContainer = Color(0xFFA2A9B8),
    secondaryContainer = Color(0xFFDFDFDF),
    onSecondaryContainer = Color.DarkGray,
    tertiaryContainer = Color(0xFFA2A9B8),
    surface = White,
    onSurface = Black,
    surfaceVariant = White,
    onSurfaceVariant = Black,

    // Фон и контуры
    background = Color(0xFFF4F4F5),
    onBackground = Black,
    outline = Color(0xFF204FC7),  // Синий
    outlineVariant = Color(0xFF0F2954)
)

private val DarkColorScheme = darkColorScheme(
    // Основные цвета
    primary = Black,
    onPrimary = White,
    secondary = Color(0xFF6393F7),  // Синий (как в светлой теме)
    onSecondary = White,

    // Третичные/дополнительные цвета
    tertiary = LightGray,  // Для текста
    onTertiary = DarkGray,

    // Контейнеры и поверхности
    primaryContainer = Gray,  // Темно-серые объекты
    onPrimaryContainer = LightGray,
    secondaryContainer = Color(0xFF202020),
    onSecondaryContainer = LightGray,
    tertiaryContainer = Color(0xFFA2A9B8),
    surface = DarkGray,
    onSurface = White,
    surfaceVariant = DarkGray,
    onSurfaceVariant = LightGray,

    // Фон и контуры
    background = DarkGray,
    onBackground = White,  // Весь текст светлый
    outline = Color(0xFF204FC7),  // Синий (как в светлой теме)
    outlineVariant = Color(0xFF6393F7)
)

@Composable
fun HSEprojectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme



    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}