package ru.autoevents.auto_events_client.feature.profile.domain.useCases

import ru.autoevents.auto_events_client.feature.profile.data.UsersRepository
import ru.autoevents.auto_events_client.feature.profile.domain.model.ProfileDataUi

class GetUserProfileUseCase(private val usersRepository: UsersRepository) {
    suspend fun getUserProfile(token: String): Result<ProfileDataUi>{
        return runCatching {
            usersRepository.me(token)
        }
    }
}