package ru.autoevents.auto_events_client.core.network.error

import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode

fun Throwable.isUnauthorizedResponse(): Boolean {
    return this is ClientRequestException && response.status == HttpStatusCode.Unauthorized
}
