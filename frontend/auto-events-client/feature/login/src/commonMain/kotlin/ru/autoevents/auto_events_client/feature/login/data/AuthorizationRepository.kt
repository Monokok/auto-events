package ru.autoevents.auto_events_client.feature.login.data

import ru.autoevents.auto_events_client.core.network.api.AuthorizationApi

class AuthorizationRepository(private val authorizationApi: AuthorizationApi) {
    suspend fun login(
        username: String, password: String
    ): String {
        return authorizationApi.login(username, password).mapToUi()
    }
}