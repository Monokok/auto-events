package ru.autoevents.auto_events_client.core.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.autoevents.auto_events_client.core.network.entity.LoginRequestDto
import ru.autoevents.auto_events_client.core.network.entity.LoginResponseDto
import ru.autoevents.auto_events_client.core.network.entity.RegisterRequestDto
import ru.autoevents.auto_events_client.core.network.entity.RegisterResponseDto

class AuthorizationApi(private val client: HttpClient) {
    suspend fun register(
        username: String, email: String, password: String
    ): RegisterResponseDto {
        return client.post("/auth/register") {

            contentType(ContentType.Application.Json)

            setBody(
                RegisterRequestDto(
                    username = username, email = email, password = password
                )
            )

        }.body()
    }

    suspend fun login(
        username: String, password: String
    ): LoginResponseDto {
        return client.post("/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(
                LoginRequestDto(
                    username, password
                )
            )
        }.body()
    }
}
