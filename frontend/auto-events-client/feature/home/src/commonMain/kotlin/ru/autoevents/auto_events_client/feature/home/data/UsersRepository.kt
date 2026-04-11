package ru.autoevents.auto_events_client.feature.home.data

import ru.autoevents.auto_events_client.core.network.api.AuthorizationApi
import ru.autoevents.auto_events_client.core.network.api.UsersApi
import ru.autoevents.auto_events_client.feature.home.domain.model.AccessTokenDataUi
import ru.autoevents.auto_events_client.feature.home.domain.model.ProfileDataUi
import ru.autoevents.auto_events_client.feature.home.domain.model.RegisterDataUi

class UsersRepository(private val usersApi: UsersApi) {
    suspend fun me(
        token: String
    ): ProfileDataUi{
        return usersApi.me(token).mapToUi()
    }
}