package ru.autoevents.auto_events_client

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.koin.core.context.startKoin
import ru.autoevents.auto_events_client.core.common.di.webModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(webModule, *appModules.toTypedArray())
    }
    ComposeViewport {
        App()
    }
}