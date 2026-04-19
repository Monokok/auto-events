package ru.autoevents.auto_events_client.feature.profile.ui.profile

import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel

class ProfileScreenModel(
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

    private fun initScreen() {


        pushState {
            it.copy(
                authState = AuthState.Unknown
            )
        }
    }

    private fun openRegister() {
        pushEffect { Effect.NavigateToRegister }
    }

    private fun openLogin() {
        pushEffect { Effect.NavigateToLogin }
    }

    private fun login(username: String, password: String) {
        // позже сюда подключишь useCase/repository
        pushState {
            it.copy(
                isLoginLoading = true
            )
        }
    }

    private fun register(username: String, email: String, password: String) {
        // позже сюда подключишь useCase/repository
        pushState {
            it.copy(
                isRegisterLoading = true
            )
        }
    }

    private fun loadProfile() {
        // загрузка профиля авторизованного пользователя
    }

    private fun logout() {
        pushState {
            it.copy(
                authState = AuthState.Unauthenticated
            )
        }
    }
}
