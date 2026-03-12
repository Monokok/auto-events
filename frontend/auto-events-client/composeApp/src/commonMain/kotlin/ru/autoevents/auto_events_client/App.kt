package ru.autoevents.auto_events_client

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.MainScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(MainScreen())
    }
}