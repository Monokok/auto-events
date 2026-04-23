package ru.autoevents.auto_events_client.feature.profile.ui.profile

import ru.autoevents.auto_events_client.core.common.tokenStorage.TokenStorage
import ru.autoevents.auto_events_client.core.network.error.isUnauthorizedResponse
import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel
import ru.autoevents.auto_events_client.feature.profile.domain.useCases.GetUserProfileUseCase

class ProfileScreenModel(
    private val tokenStorage: TokenStorage,
    private val getUserProfileUseCase: GetUserProfileUseCase,
) : MviScreenModel<Effect, Action, State>(
    defaultState = State()
) {

    init {
        pushAction(Action.Init)
    }

    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.Init -> initScreen()
            Action.LoadProfile -> loadProfile()
            Action.Logout -> logout()
        }
    }

    private suspend fun initScreen() {
        pushState {
            it.copy(
                authState = AuthState.Unknown,
                isLoading = true,
            )
        }

        loadProfile()
    }

    private suspend fun loadProfile() {
        val token = tokenStorage.getAccessToken()
        if (token.isNullOrBlank()) {
            redirectToLogin()
            return
        }

        getUserProfileUseCase.invoke(token)
            .onSuccess { profile ->
                pushState {
                    it.copy(
                        authState = AuthState.Authenticated(profile),
                        isLoading = false,
                    )
                }
            }
            .onFailure { error ->
                if (error.isUnauthorizedResponse()) {
                    redirectToLogin()
                } else {
                    val message = error.message ?: "Failed to load profile"
                    pushState {
                        it.copy(
                            authState = AuthState.Error(message, error),
                            isLoading = false,
                        )
                    }
                    pushEffect { Effect.ShowError(message) }
                }
            }
    }

    private fun logout() {
        redirectToLogin()
    }

    private fun redirectToLogin() {
        pushState {
            it.copy(
                authState = AuthState.Unauthenticated,
                isLoading = false,
            )
        }
        pushEffect { Effect.NavigateToLogin }
    }
}
