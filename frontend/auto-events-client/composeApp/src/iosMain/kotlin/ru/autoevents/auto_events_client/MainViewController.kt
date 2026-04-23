package ru.autoevents.auto_events_client

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoinOrNull
import platform.UIKit.UIViewController
import ru.autoevents.auto_events_client.core.common.di.iosModule

fun MainViewController(): UIViewController {
    if (getKoinOrNull() == null) {
        startKoin { modules(iosModule, *appModules.toTypedArray()) }
    }
    return ComposeUIViewController { App() }
}