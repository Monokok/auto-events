package ru.autoevents.auto_events_client.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Основные цвета
private val Color.Companion.LightThemeWhite950: Color get() = Color(0xFFFAFAFA)
private val Color.Companion.LightThemePrimary900: Color get() = Color(0xFF7952FC)
private val Color.Companion.LightThemeDark900: Color get() = Color(0xFF13123A)
private val Color.Companion.LightThemeDark700: Color get() = Color(0xFF7A7A90)
private val Color.Companion.LightThemeSecondary50: Color get() = Color(0xFFF4E9FA)

// DARK THEME COLORS
private val Color.Companion.DarkThemeBackground: Color get() = Color(0xFF0B0D1A)
private val Color.Companion.DarkThemeSurface: Color get() = Color(0xFF15182E)

private val Color.Companion.DarkThemePrimary: Color get() = Color(0xFF6C5CE7)
private val Color.Companion.DarkThemeAccent: Color get() = Color(0xFF00D4FF)

private val Color.Companion.DarkThemeTextMain: Color get() = Color(0xFFF1F2F6)
private val Color.Companion.DarkThemeTextSecondary: Color get() = Color(0xFF8C90A8)

private val Color.Companion.DarkThemeBorder: Color get() = Color(0xFF2A2E4A)

val ColorScheme.white950
    @Composable get() = if (isSystemInDarkTheme()) {
        Color.DarkThemeBackground
    } else {
        Color.LightThemeWhite950
    }

val ColorScheme.dark900
    @Composable get() = if (isSystemInDarkTheme()) {
        Color.DarkThemeTextMain
    } else {
        Color.LightThemeDark900
    }

val ColorScheme.dark700
    @Composable get() = if (isSystemInDarkTheme()) {
        Color.DarkThemeTextSecondary
    } else {
        Color.LightThemeDark700
    }

val ColorScheme.primary900
    @Composable get() = if (isSystemInDarkTheme()) {
        Color.DarkThemePrimary
    } else {
        Color.LightThemePrimary900
    }

val ColorScheme.secondary50
    @Composable get() = if (isSystemInDarkTheme()) {
        Color.DarkThemeSurface
    } else {
        Color.LightThemeSecondary50
    }

val ColorScheme.accent
    @Composable get() = if (isSystemInDarkTheme()) {
        Color.DarkThemeAccent
    } else {
        Color.LightThemePrimary900
    }

val ColorScheme.border
    @Composable get() = if (isSystemInDarkTheme()) {
        Color.DarkThemeBorder
    } else {
        Color(0xFFE0E0E0)
    }

val ColorScheme.white900
    @Composable get() = if (isSystemInDarkTheme()) {
        Color.DarkThemeSurface
    } else {
        Color.White
    }


val ColorScheme.diagonalGradient: Brush
    @Composable get() = Brush.linearGradient(
        colors = listOf(
            primary,
            Color(0xFFB15CDE),
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f) // диагональ
    )
