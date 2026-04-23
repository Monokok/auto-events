package ru.autoevents.auto_events_client.feature.login.ui.login

import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.feature.login.ui.login.web.LoginContent

@Composable
internal actual fun LoginScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    LoginContent(
        state = state,
        onAction = onAction
    )
}