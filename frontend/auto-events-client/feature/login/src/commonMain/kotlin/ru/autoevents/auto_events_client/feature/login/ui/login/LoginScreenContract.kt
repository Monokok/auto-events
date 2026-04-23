package ru.autoevents.auto_events_client.feature.login.ui.login

import ru.autoevents.auto_events_client.core.ui.mvi.MviAction
import ru.autoevents.auto_events_client.core.ui.mvi.MviEffect
import ru.autoevents.auto_events_client.core.ui.mvi.MviState
import ru.autoevents.auto_events_client.feature.login.domain.model.ProfileDataUi

sealed interface Effect : MviEffect {
    object NavigateToRegister : Effect
    object LoginSucceeded : Effect
}

sealed interface Action : MviAction {
    object Init : Action
    data class Login(val username: String, val password: String) : Action
    object OpenRegister : Action
}

data class State(
    val isLoading: Boolean = false,
    val authState: AuthState = AuthState.LoggingIn(),
) : MviState

sealed interface AuthState {
    data class LoggingIn(
        val username: String = "",
        val password: String = "",
        val error: String? = null,
        val message: String? = null,
    ) : AuthState

    data class Authorized(
        val profile: ProfileDataUi,
    ) : AuthState
}
