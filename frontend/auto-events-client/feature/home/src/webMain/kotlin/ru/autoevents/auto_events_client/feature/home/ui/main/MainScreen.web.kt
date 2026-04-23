package ru.autoevents.auto_events_client.feature.home.ui.main

import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.feature.home.ui.main.web.MainContent

@Composable
internal actual fun MainScreenContent(
    state: State,
    onAction: (Action) -> Unit,
    navigateToEventInfo: (Int) -> Unit,
) {
    MainContent(state, onAction, navigateToEventInfo)
}

@Composable
internal actual fun MainScreenBottomBar(navigateToMain: () -> Unit, navigateToProfile: () -> Unit) {
}