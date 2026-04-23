package ru.autoevents.auto_events_client.core.ui.models

import coil3.ComponentRegistry
import io.ktor.client.*

actual fun ComponentRegistry.Builder.addNetworkFetcher(httpClient: HttpClient): ComponentRegistry.Builder = this