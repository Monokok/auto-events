package ru.autoevents.auto_events_client.core.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.autoevents.auto_events_client.core.network.entity.CityDto

class VenueApi(private val client: HttpClient) {
    suspend fun getCities(): List<CityDto> {
        return client.get("/cities").body()
    }
}

