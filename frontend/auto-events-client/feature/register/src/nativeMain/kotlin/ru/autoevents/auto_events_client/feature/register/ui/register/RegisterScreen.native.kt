package ru.autoevents.auto_events_client.feature.register.ui.register

import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.feature.register.ui.register.web.RegisterContent

@Composable
internal actual fun RegisterScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    //TODO: profileContentMobile

    RegisterContent(
        state = state,
        onAction = onAction,
    )
}