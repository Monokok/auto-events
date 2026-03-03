package ru.autoevents.auto_events_client

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(appModules)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "auto_events_client",
    ) {
        App()
    }
}