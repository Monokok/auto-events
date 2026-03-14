package ru.autoevents.auto_events_client.core.network.client

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import ru.autoevents.auto_events_client.core.common.BASE_URL

/**
 * Создаёт экземпляр HttpClient с предустановленной поддержкой JSON.
 * Параметр engine позволяет передать платформенный движок (ожидается actual-реализация при необходимости).
 */

//HttpClientEngine можно провайдить отдельно для каждой платформы свой, но пока что хорошо работает и с дефолтным
fun createHttpClient(engine: HttpClientEngine? = null): HttpClient {
    return engine?.let {
        HttpClient(it) {
            init()
        }
    } ?: HttpClient {
        init()
    }
}

fun HttpClientConfig<*>.init() = run {
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
    defaultRequest {
        url.takeFrom(BASE_URL)      //Установка адреса сервера
        contentType(ContentType.Application.Json)
    }
}