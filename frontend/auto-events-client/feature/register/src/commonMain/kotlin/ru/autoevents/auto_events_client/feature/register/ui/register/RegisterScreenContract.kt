package ru.autoevents.auto_events_client.feature.register.ui.register

import ru.autoevents.auto_events_client.core.ui.mvi.MviAction
import ru.autoevents.auto_events_client.core.ui.mvi.MviEffect
import ru.autoevents.auto_events_client.core.ui.mvi.MviState

sealed interface Effect : MviEffect {
    object NavigateBackToLogin : Effect
}

sealed interface Action : MviAction {
    object Init : Action
    object OpenLogin: Action
    data class Register(val username: String, val email: String, val password: String) : Action
}

data class State(
    val isLoading: Boolean = false, val authState: AuthState = AuthState.Unauthorized
) : MviState

/**
 * Состояние авторизации. Единая точка для отслеживания авторизован пользователь или нет
 */
sealed interface AuthState {

    object Unauthorized : AuthState

    data class Registering(
        val email: String = "",
        val username: String = "",
        val password: String = "",
        val error: String? = null,
        val isLoading: Boolean = false
    ) : AuthState
}
