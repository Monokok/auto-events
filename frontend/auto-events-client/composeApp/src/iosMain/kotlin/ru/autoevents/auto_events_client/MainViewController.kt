package ru.autoevents.auto_events_client

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoinOrNull
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    if (getKoinOrNull() == null) {
        startKoin { modules(appModules) }
    }
    return ComposeUIViewController { App() }
}