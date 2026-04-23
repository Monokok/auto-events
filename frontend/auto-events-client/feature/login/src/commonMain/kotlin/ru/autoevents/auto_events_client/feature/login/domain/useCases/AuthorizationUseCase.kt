package ru.autoevents.auto_events_client.feature.login.domain.useCases

import ru.autoevents.auto_events_client.feature.login.data.AuthorizationRepository

class AuthorizationUseCase(
        val authorizationRepository: AuthorizationRepository,
    ) {
    suspend fun login(login: String, password: String): Result<String> {
        return runCatching { authorizationRepository.login(login, password) }
    }
}