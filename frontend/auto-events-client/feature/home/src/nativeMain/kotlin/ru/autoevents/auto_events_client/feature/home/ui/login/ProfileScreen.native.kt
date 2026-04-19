package ru.autoevents.auto_events_client.feature.home.ui.login

import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.feature.home.ui.login.web.ProfileContent

@Composable
internal actual fun ProfileScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    //TODO: profileContentMobile

    ProfileContent(
        state = state,
        onAction = onAction,
    )
}