package ru.autoevents.auto_events_client.core.network.client

import io.ktor.client.*

actual fun createHttpClient(): HttpClient =
    HttpClient {
        init()
    }