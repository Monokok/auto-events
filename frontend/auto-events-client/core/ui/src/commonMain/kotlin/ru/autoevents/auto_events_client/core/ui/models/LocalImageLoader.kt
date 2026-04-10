package ru.autoevents.auto_events_client.core.ui.models

import androidx.compose.runtime.staticCompositionLocalOf
import coil3.ComponentRegistry
import coil3.ImageLoader
import io.ktor.client.*

val LocalImageLoader = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided")
}

expect fun ComponentRegistry.Builder.addNetworkFetcher(httpClient: HttpClient): ComponentRegistry.Builder