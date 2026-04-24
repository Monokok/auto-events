package ru.autoevents.auto_events_client.feature.profile.ui.profile

import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.feature.profile.ui.profile.web.ProfileContent

@Composable
internal actual fun ProfileScreenContent(
    state: State,
    onAction: (Action) -> Unit,
    navigateLogin: () -> Unit,
) {
    //TODO: profileContentMobile

    ProfileContent(
        state = state,
        onAction = onAction,
        navigateLogin = navigateLogin,
    )
}