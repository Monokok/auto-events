package ru.autoevents.auto_events_client.feature.home.ui.profile

import auto_events_client.feature.home.generated.resources.Res
import auto_events_client.feature.home.generated.resources.failed_login
import auto_events_client.feature.home.generated.resources.failed_to_get_profile
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel
import ru.autoevents.auto_events_client.feature.home.domain.model.ProfileDataUi
import ru.autoevents.auto_events_client.feature.home.domain.useCases.AuthorizationUseCase

class ProfileScreenModel(
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

            is Action.Register -> register(action.username, action.email, action.password)

            //юзверь хочет перейти к регистрации
            Action.OpenRegister -> {
                pushState {
                    it.copy(
                        authState = AuthState.Registering(
                            //в теории здесь можно подхватить какие-то данные, которые по умолчанию будут подхватываться в UI
                        ) //на основе данного состояния UI среагирует и отрисует RegisterComponent()
                    )
                }
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

    private suspend fun register(username: String, email: String, password: String) {
        authorizationUseCase.register(username, email, password)
            .onSuccess {
                // Успешная регистрация - переходим на экран логина
                pushState {
                    it.copy(
                        isLoading = false,
                        authState = AuthState.LoggingIn(
                            username = username,
                            password = password,
                            message = "Регистрация успешна! Теперь войдите"
                        )
                    )
                }
            }
            .onFailure { error ->
                // Ошибка регистрации - остаемся на экране регистрации
                pushState {
                    it.copy(
                        isLoading = false,
                        authState = AuthState.Registering(
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