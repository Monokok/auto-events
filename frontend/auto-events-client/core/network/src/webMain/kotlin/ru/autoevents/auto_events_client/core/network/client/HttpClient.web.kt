package ru.autoevents.auto_events_client.core.network.client

import io.ktor.client.*
import io.ktor.client.engine.js.*

actual fun createHttpClient(): io.ktor.client.HttpClient =
    HttpClient(Js) {
        init()
    }
