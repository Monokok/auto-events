package ru.autoevents.auto_events_client.core.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.autoevents.auto_events_client.core.network.entity.EventResponseDto
import ru.autoevents.auto_events_client.core.network.entity.EventTypeResponseDto

class EventApi(private val client: HttpClient) {

    suspend fun getEvents(cityId: Int?, typeId: Int?): EventResponseDto {
        return client.get("/events"){
            url {
                cityId?.let { parameter("city_id", it) }
                typeId?.let { parameter("type_id", it) }
            }
        }.body()
    }

    suspend fun getEventsTypes(): List<EventTypeResponseDto>{
        return client.get("/events/types").body()
    }
}