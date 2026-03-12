package ru.autoevents.auto_events_client.feature.home.ui.mainScreen.mobile

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.core.ui.components.MobilePreview
import ru.autoevents.auto_events_client.core.ui.components.Screen
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.Action
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.State

@Composable
internal fun ScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    Screen {
        Text(text = "Hello World",
            style = MaterialTheme.typography.inter14Normal,
            color = MaterialTheme.colorScheme.dark900)
    }
}

@Composable
@MobilePreview
private fun MobileScreenPreview() {
    MaterialTheme {
        ScreenContent(
            state = State(),
            onAction = {}
        )
    }
}