package ru.autoevents.auto_events_client.feature.login.ui.login

import ru.autoevents.auto_events_client.core.ui.mvi.MviAction
import ru.autoevents.auto_events_client.core.ui.mvi.MviEffect
import ru.autoevents.auto_events_client.core.ui.mvi.MviState
import ru.autoevents.auto_events_client.feature.login.domain.model.ProfileDataUi

sealed interface Effect : MviEffect

sealed interface Action : MviAction {
    object Init : Action
    data class Login(val username: String, val password: String) : Action
    object OpenRegister : Action
    object OpenLogin : Action
}

data class State(
    val isLoading: Boolean = false,
    val authState: AuthState = AuthState.Unauthorized
) : MviState

/**
 * Состояние авторизации. Единая точка для отслеживания авторизован пользователь или нет
 */
sealed interface AuthState {

    object Unauthorized : AuthState

    data class Error(val message: String, val cause: Throwable? = null) : AuthState

    data class LoggingIn(
        val username: String = "",
        val password: String = "",
        val error: String? = null,
        val message: String? = null,
        val isLoading: Boolean = false
    ) : AuthState

    data class Authorized(
        val profile: ProfileDataUi,
        val accessToken: String
    ) : AuthState
}
