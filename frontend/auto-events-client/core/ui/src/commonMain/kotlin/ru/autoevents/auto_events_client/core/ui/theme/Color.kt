package ru.autoevents.auto_events_client.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Основные цвета
private val Color.Companion.LightThemeWhite950: Color get() = Color(0xFFFAFAFA)
private val Color.Companion.LightThemePrimary900: Color get() = Color(0xFF7952FC)
private val Color.Companion.LightThemeDark900: Color get() = Color(0xFF13123A)
private val Color.Companion.LightThemeDark700: Color get() = Color(0xFF7A7A90)
private val Color.Companion.LightThemeSecondary50: Color get() = Color(0xFFF4E9FA)

val ColorScheme.white900
    @Composable
    get() = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.White
    }

val ColorScheme.white950
    @Composable
    get() = if (isSystemInDarkTheme()) {
        Color.LightThemeWhite950
    } else {
        Color.LightThemeWhite950
    }

val ColorScheme.dark900
    @Composable
    get() = if (isSystemInDarkTheme()) {
        Color.LightThemeDark900
    } else {
        Color.LightThemeDark900
    }

val ColorScheme.dark700
    @Composable
    get() = if (isSystemInDarkTheme()) {
        Color.LightThemeDark700
    } else {
        Color.LightThemeDark700
    }

val ColorScheme.primary900
    @Composable
    get() = if (isSystemInDarkTheme()) {
        Color.LightThemePrimary900
    } else {
        Color.LightThemePrimary900
    }

val ColorScheme.secondary50
    @Composable
    get() = if (isSystemInDarkTheme()) {
        Color.LightThemeSecondary50
    } else {
        Color.LightThemeSecondary50
    }