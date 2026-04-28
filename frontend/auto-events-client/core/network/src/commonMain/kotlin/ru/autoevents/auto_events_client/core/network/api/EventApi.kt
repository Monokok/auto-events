package ru.autoevents.auto_events_client.core.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.autoevents.auto_events_client.core.network.entity.EventDto
import ru.autoevents.auto_events_client.core.network.entity.EventResponseDto
import ru.autoevents.auto_events_client.core.network.entity.EventTypeResponseDto

class EventApi(private val client: HttpClient) {

    suspend fun getEvents(cityId: Int?, typeId: Int?, page: Int?, size: Int?): EventResponseDto {
        return client.get("/events") {
            url {
                cityId?.let { parameter("city_id", it) }
                typeId?.let { parameter("type_id", it) }
                page?.let { parameter("page", it) }
                size?.let { parameter("size", it) }
            }
        }.body()
    }

    suspend fun getEventsTypes(): List<EventTypeResponseDto> {
        return client.get("/events/types").body()
    }

    suspend fun getEvent(eventId: Int): EventDto {
        return client.get("/events/$eventId").body()
    }
}