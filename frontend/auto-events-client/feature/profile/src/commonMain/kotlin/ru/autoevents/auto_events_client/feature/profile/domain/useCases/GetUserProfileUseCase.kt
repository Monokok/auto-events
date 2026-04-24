package ru.autoevents.auto_events_client.feature.profile.domain.useCases

import ru.autoevents.auto_events_client.feature.profile.data.UsersRepository
import ru.autoevents.auto_events_client.feature.profile.domain.model.ProfileDataUi

class GetUserProfileUseCase(val usersRepository: UsersRepository) {
    suspend operator fun invoke(token: String): Result<ProfileDataUi>{
        return runCatching {
            usersRepository.me(token)
        }
    }
}