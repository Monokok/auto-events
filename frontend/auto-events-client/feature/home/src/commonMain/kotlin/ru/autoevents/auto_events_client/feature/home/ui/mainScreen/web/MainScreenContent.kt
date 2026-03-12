package ru.autoevents.auto_events_client.feature.home.ui.mainScreen.web

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.core.ui.components.Screen
import ru.autoevents.auto_events_client.core.ui.components.WebPreview
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter48ExtraBold
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.Action
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.State


@Composable
internal fun ScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    Screen {
        Text(
            text = "Hello World",
            style = MaterialTheme.typography.inter48ExtraBold,
            color = MaterialTheme.colorScheme.dark900
        )
    }
}

@Composable
@WebPreview
private fun WebScreenPreview() {
    MaterialTheme {
        ScreenContent(
            state = State(),
            onAction = {}
        )
    }
}