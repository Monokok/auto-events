package ru.autoevents.auto_events_client.feature.login.data

import ru.autoevents.auto_events_client.core.network.api.UsersApi
import ru.autoevents.auto_events_client.feature.login.domain.model.ProfileDataUi

class UsersRepository(private val usersApi: UsersApi) {
    suspend fun me(
        token: String
    ): ProfileDataUi{
        return usersApi.me(token).mapToUi()
    }
}