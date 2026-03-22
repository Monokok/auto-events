package ru.autoevents.auto_events_client.core.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.autoevents.auto_events_client.core.network.entity.CityResponseDto

class CityApi(private val client: HttpClient) {
    suspend fun getCities(): CityResponseDto {
        return client.get("/cities").body()
    }
}

