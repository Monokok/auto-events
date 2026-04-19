package ru.autoevents.auto_events_client.feature.login.ui.login

import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel
import ru.autoevents.auto_events_client.feature.login.domain.useCases.AuthorizationUseCase

class LoginScreenModel(
    private val authorizationUseCase: AuthorizationUseCase,
) : MviScreenModel<Effect, Action, State>(
    defaultState = State(),
) {
    init {
        pushAction(Action.OpenRegister)
    }

    override suspend fun handleAction(action: Action) {
        when (action) {

            Action.Init -> pushAction(Action.Init)


            //юзверь хочет перейти к регистрации
            Action.OpenRegister -> {
//                pushState {
//                    it.copy(
//                        authState = AuthState.Registering(
//                            //в теории здесь можно подхватить какие-то данные, которые по умолчанию будут подхватываться в UI
//                        ) //на основе данного состояния UI среагирует и отрисует RegisterComponent()
//                    )
//                }
            }

            Action.OpenLogin -> {
                pushState {
                    it.copy(
                        authState = AuthState.LoggingIn()
                    )
                }
            }

            is Action.Login -> {
                login(action.username, action.password)
            }

            else -> Unit
        }
    }

    private suspend fun login(username: String, password: String) {
        authorizationUseCase.login(username, password)
            .onSuccess { token ->
                authorizationUseCase.getUserProfile(token)
                    .onSuccess { profile ->
                        pushState {
                            it.copy(
                                isLoading = false,
                                authState = AuthState.Authorized(
                                    profile = profile,
                                    accessToken = token
                                )
                            )
                        }
                    }
                    .onFailure { error ->
                        // Ошибка получения профиля - остаемся на экране логина
                        pushState {
                            it.copy(
                                isLoading = false,
                                authState = AuthState.LoggingIn(
                                    username = username,
                                    password = password,
                                    error = error.message ?: "Ошибка получения данных профиля"
                                )
                            )
                        }
                    }
            }
            .onFailure { error ->
                // Ошибка логина - остаемся на экране логина
                pushState {
                    it.copy(
                        isLoading = false,
                        authState = AuthState.LoggingIn(
                            username = username,
                            password = password,
                            error = error.message ?: "Неверный логин или пароль"
                        )
                    )
                }
            }
    }
}