package ru.autoevents.auto_events_client.feature.login.domain.useCases

import ru.autoevents.auto_events_client.feature.login.data.AuthorizationRepository
import ru.autoevents.auto_events_client.feature.login.data.UsersRepository
import ru.autoevents.auto_events_client.feature.login.domain.model.ProfileDataUi

class AuthorizationUseCase(
        val authorizationRepository: AuthorizationRepository,
        val usersRepository: UsersRepository
    ) {
    suspend fun login(login: String, password: String): Result<String> {
        return runCatching { authorizationRepository.login(login, password) }
    }

    suspend fun getUserProfile(token: String): Result<ProfileDataUi>{
        return runCatching {
            usersRepository.me(token)
        }
    }
}