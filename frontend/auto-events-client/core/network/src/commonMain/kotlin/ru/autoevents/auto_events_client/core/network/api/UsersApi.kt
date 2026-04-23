package ru.autoevents.auto_events_client.core.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.autoevents.auto_events_client.core.network.entity.LoginRequestDto
import ru.autoevents.auto_events_client.core.network.entity.LoginResponseDto
import ru.autoevents.auto_events_client.core.network.entity.RegisterRequestDto
import ru.autoevents.auto_events_client.core.network.entity.RegisterResponseDto
import ru.autoevents.auto_events_client.core.network.entity.UserResponseDto

class UsersApi(private val client: HttpClient) {
    suspend fun me(
        token: String
    ): UserResponseDto {
        return client.get("/users/me") {

            // Добавляем токен в заголовок Authorization
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body()
    }
}
