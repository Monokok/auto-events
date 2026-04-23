package ru.autoevents.auto_events_client.feature.profile.ui.profile

import ru.autoevents.auto_events_client.core.common.auth.AccessTokenProvider
import ru.autoevents.auto_events_client.core.network.error.isUnauthorizedResponse
import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel
import ru.autoevents.auto_events_client.feature.profile.domain.useCases.GetUserProfileUseCase

class ProfileScreenModel(
    private val accessTokenProvider: AccessTokenProvider,
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
            Action.OpenRegister -> openRegister()
            Action.OpenLogin -> openLogin()
            is Action.Login -> login(action.username, action.password)
            is Action.Register -> register(action.username, action.email, action.password)
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

    private fun openRegister() {
        pushEffect { Effect.NavigateToRegister }
    }

    private fun openLogin() {
        pushEffect { Effect.NavigateToLogin }
    }

    private fun login(username: String, password: String) {
        pushState {
            it.copy(
                isLoginLoading = true
            )
        }
    }

    private fun register(username: String, email: String, password: String) {
        pushState {
            it.copy(
                isRegisterLoading = true
            )
        }
    }

    private suspend fun loadProfile() {
        val token = accessTokenProvider.getAccessToken()?.takeIf { it.isNotBlank() }
        if (token == null) {
            redirectToLogin()
            return
        }

        getUserProfileUseCase.getUserProfile(token)
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
