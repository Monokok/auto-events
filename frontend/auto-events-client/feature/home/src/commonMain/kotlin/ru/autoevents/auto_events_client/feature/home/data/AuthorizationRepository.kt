package ru.autoevents.auto_events_client.feature.home.data

import ru.autoevents.auto_events_client.core.network.api.AuthorizationApi
import ru.autoevents.auto_events_client.feature.home.domain.model.RegisterDataUi

class AuthorizationRepository(private val authorizationApi: AuthorizationApi) {
    suspend fun register(
        username: String, email: String, password: String
    ): RegisterDataUi{
        return authorizationApi.register(username, email, password).mapToUi()
    }

    suspend fun login(
        username: String, password: String
    ): String {
        return authorizationApi.login(username, password).mapToUi()
    }
}