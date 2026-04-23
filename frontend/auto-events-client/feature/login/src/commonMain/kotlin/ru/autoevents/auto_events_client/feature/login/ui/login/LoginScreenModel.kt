package ru.autoevents.auto_events_client.feature.login.ui.login

import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel
import ru.autoevents.auto_events_client.feature.login.domain.useCases.AuthorizationUseCase

class LoginScreenModel(
    private val authorizationUseCase: AuthorizationUseCase,
) : MviScreenModel<Effect, Action, State>(
    defaultState = State(),
) {
    override suspend fun handleAction(action: Action) {
        when (action) {
            Action.Init -> initScreen()
            Action.OpenRegister -> openRegister()
            is Action.Login -> login(action.username, action.password)
        }
    }

    private fun initScreen() {
        pushState {
            it.copy(
                isLoading = false,
                authState = AuthState.LoggingIn(),
            )
        }
    }

    private fun openRegister() {
        pushEffect { Effect.NavigateToRegister }
    }

    private suspend fun login(username: String, password: String) {
        pushState {
            it.copy(
                isLoading = true,
                authState = AuthState.LoggingIn(
                    username = username,
                    password = password,
                ),
            )
        }

        authorizationUseCase.login(username, password)
            .onSuccess { token ->
                authorizationUseCase.getUserProfile(token)
                    .onSuccess { profile ->
//                        pushEffect { Effect.LoginSucceeded }
                        pushState {
                            it.copy(
                                isLoading = false,
                                authState = AuthState.Authorized(
                                    profile = profile,
                                    accessToken = token,
                                ),
                            )
                        }
                    }
                    .onFailure { error ->
                        pushLoginError(
                            username = username,
                            password = password,
                            message = error.message ?: "Failed to load profile",
                        )
                    }
            }
            .onFailure { error ->
                pushLoginError(
                    username = username,
                    password = password,
                    message = error.message ?: "Invalid username or password",
                )
            }
    }

    private fun pushLoginError(
        username: String,
        password: String,
        message: String,
    ) {
        pushState {
            it.copy(
                isLoading = false,
                authState = AuthState.LoggingIn(
                    username = username,
                    password = password,
                    error = message,
                ),
            )
        }
    }
}
