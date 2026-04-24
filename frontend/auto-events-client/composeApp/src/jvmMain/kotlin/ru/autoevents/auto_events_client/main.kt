package ru.autoevents.auto_events_client

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "auto_events_client",
    ) {
        App()
    }
}