package ru.autoevents.auto_events_client.core.network.client

import io.ktor.client.*

actual fun createHttpClient(): io.ktor.client.HttpClient =
    HttpClient() {
        init()
    }