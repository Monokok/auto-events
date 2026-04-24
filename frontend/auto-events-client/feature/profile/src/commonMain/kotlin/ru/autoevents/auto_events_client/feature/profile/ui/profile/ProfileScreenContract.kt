package ru.autoevents.auto_events_client.feature.profile.ui.profile

import ru.autoevents.auto_events_client.core.ui.mvi.MviAction
import ru.autoevents.auto_events_client.core.ui.mvi.MviEffect
import ru.autoevents.auto_events_client.core.ui.mvi.MviState
import ru.autoevents.auto_events_client.feature.profile.domain.model.ProfileDataUi

sealed interface Effect : MviEffect {
    data class ShowError(val message: String) : Effect
    object NavigateToLogin : Effect
    object NavigateToRegister : Effect
}

sealed interface Action : MviAction {
    object Init : Action
    object Logout : Action
    object LoadProfile : Action

}

data class State(
    val authState: AuthState = AuthState.Unknown,
    val isLoading: Boolean = false,
    val isLoginLoading: Boolean = false,
    val isRegisterLoading: Boolean = false,
) : MviState

sealed interface AuthState {
    object Unknown : AuthState            // еще не проверили сессию
    object Unauthenticated : AuthState    // не вошел
    data class Authenticated(val user: ProfileDataUi) : AuthState
    data class Error(val message: String, val cause: Throwable? = null) : AuthState
}