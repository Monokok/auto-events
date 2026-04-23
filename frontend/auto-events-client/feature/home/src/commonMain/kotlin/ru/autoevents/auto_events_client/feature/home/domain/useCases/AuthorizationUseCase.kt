package ru.autoevents.auto_events_client.feature.home.domain.useCases

import ru.autoevents.auto_events_client.feature.home.data.AuthorizationRepository
import ru.autoevents.auto_events_client.feature.home.data.UsersRepository
import ru.autoevents.auto_events_client.feature.home.domain.model.ProfileDataUi
import ru.autoevents.auto_events_client.feature.home.domain.model.RegisterDataUi

class AuthorizationUseCase(
        val authorizationRepository: AuthorizationRepository,
        val usersRepository: UsersRepository
    ) {
    suspend fun register(login: String, email: String, password: String): Result<RegisterDataUi> {
        return runCatching { authorizationRepository.register(login, email, password) }
    }

    suspend fun login(login: String, password: String): Result<String> {
        return runCatching { authorizationRepository.login(login, password) }
    }

    suspend fun getUserProfile(token: String): Result<ProfileDataUi>{
        return runCatching {
            usersRepository.me(token)
        }
    }
}