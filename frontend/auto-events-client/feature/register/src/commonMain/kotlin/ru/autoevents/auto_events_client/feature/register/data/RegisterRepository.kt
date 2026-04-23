package ru.autoevents.auto_events_client.feature.register.data

import ru.autoevents.auto_events_client.core.network.api.AuthorizationApi
import ru.autoevents.auto_events_client.feature.register.domain.model.RegisterDataUi

class RegisterRepository(private val authorizationApi: AuthorizationApi) {
    suspend fun register(
        username: String, email: String, password: String
    ): RegisterDataUi{
        return authorizationApi.register(username, email, password).mapToUi()
    }
}