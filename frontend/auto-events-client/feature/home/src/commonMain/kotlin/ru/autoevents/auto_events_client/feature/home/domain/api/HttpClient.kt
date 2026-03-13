package ru.autoevents.auto_events_client.feature.home.domain.api

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

/**
 * ПЕРЕНЕСТИ В КОР
 * Создаёт экземпляр HttpClient с предустановленной поддержкой JSON.
 * Параметр engine позволяет передать платформенный движок (ожидается actual-реализация при необходимости).
 */
fun createHttpClient(engine: HttpClientEngine? = null): HttpClient {
    return engine?.let {
        HttpClient(it) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true      // игнорировать неизвестные поля в JSON
                    isLenient = true               // допускать нестрогий синтаксис
                    coerceInputValues = true       // преобразовывать некорректные значения в null
                    explicitNulls = false          // не сериализовать null-поля
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.SIMPLE
            }
        }
    } ?: HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true      // игнорировать неизвестные поля в JSON
                isLenient = true               // допускать нестрогий синтаксис
                coerceInputValues = true       // преобразовывать некорректные значения в null
                explicitNulls = false          // не сериализовать null-поля
            })
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.SIMPLE
        }
    }
}