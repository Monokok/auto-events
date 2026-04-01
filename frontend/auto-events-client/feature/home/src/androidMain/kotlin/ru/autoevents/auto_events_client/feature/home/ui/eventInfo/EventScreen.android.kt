package ru.autoevents.auto_events_client.feature.home.ui.eventInfo

import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.feature.home.ui.eventInfo.mobile.EventInfoContent

@Composable
internal actual fun EventInfoScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    EventInfoContent(state = state)
}