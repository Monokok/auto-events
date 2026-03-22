package ru.autoevents.auto_events_client.core.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.autoevents.auto_events_client.core.network.entity.EventResponseDto

class EventApi(private val client: HttpClient) {

    suspend fun getEvents(): EventResponseDto {
        return client.get("/event").body()
    }
}