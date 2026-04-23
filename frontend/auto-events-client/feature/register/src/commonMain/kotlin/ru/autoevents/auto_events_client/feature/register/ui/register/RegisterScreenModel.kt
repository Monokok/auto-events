package ru.autoevents.auto_events_client.feature.register.ui.register

import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel
import ru.autoevents.auto_events_client.feature.register.domain.useCases.RegisterUseCase

class RegisterScreenModel(
    private val authorizationUseCase: RegisterUseCase,
) : MviScreenModel<Effect, Action, State>(
    defaultState = State(),
) {
    init {
        //TODO: skeleton-template-ui
    }

    override suspend fun handleAction(action: Action) {
        when (action) {

            Action.Init -> pushAction(Action.Init)

            Action.OpenLogin -> openLogin()

            is Action.Register -> register(action.username, action.email, action.password)

            else -> {}
        }
    }

    private fun openLogin() {
        pushEffect { Effect.NavigateBackToLogin }
    }

    private suspend fun register(username: String, email: String, password: String) {
        authorizationUseCase.register(username, email, password).onSuccess {
                // Успешная регистрация - переходим на экран логина
                pushEffect { Effect.NavigateBackToLogin }
            }.onFailure { error ->
                // Ошибка регистрации - остаемся на экране регистрации
                pushState {
                    it.copy(
                        isLoading = false, authState = AuthState.Registering(
                            email = email,
                            username = username,
                            password = password,
                            error = error.message ?: "Ошибка регистрации"
                        )
                    )
                }
            }
    }
}
