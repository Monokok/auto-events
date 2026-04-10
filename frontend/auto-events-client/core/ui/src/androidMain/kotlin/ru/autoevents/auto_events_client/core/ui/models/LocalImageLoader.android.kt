package ru.autoevents.auto_events_client.core.ui.models

import coil3.ComponentRegistry
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.ktor.client.*

actual fun ComponentRegistry.Builder.addNetworkFetcher(httpClient: HttpClient): ComponentRegistry.Builder =
    add(
        KtorNetworkFetcherFactory(
            httpClient = httpClient,
        )
    )