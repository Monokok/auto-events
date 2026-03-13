package ru.autoevents.auto_events_client.feature.home.domain.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.autoevents.auto_events_client.feature.home.domain.entity.EventResponseDto

class EventApi(private val client: HttpClient, private val baseUrl: String) {
    /**
     * Выполняет GET-запрос к /event и возвращает десериализованный ответ.
     * Предполагается, что базовый URL уже задан в клиенте (можно передать через конфигурацию).
     */
    suspend fun getEvents(): EventResponseDto {
        return client.get("$baseUrl/event").body()
    }
}