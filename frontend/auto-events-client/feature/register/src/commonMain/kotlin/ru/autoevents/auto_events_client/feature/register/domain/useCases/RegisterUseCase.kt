package ru.autoevents.auto_events_client.feature.register.domain.useCases

import ru.autoevents.auto_events_client.feature.register.data.RegisterRepository
import ru.autoevents.auto_events_client.feature.register.domain.model.RegisterDataUi

class RegisterUseCase(
    val authorizationRepository: RegisterRepository,
    ) {
    suspend fun register(login: String, email: String, password: String): Result<RegisterDataUi> {
        return runCatching { authorizationRepository.register(login, email, password) }
    }
}