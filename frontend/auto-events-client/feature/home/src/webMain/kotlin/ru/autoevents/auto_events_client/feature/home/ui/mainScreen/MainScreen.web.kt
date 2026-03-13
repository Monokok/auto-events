package ru.autoevents.auto_events_client.feature.home.ui.mainScreen

import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.web.ScreenContent

@Composable
internal actual fun MainScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    ScreenContent(state, onAction)
}