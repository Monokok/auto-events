package ru.autoevents.auto_events_client.feature.profile.ui.profile.web

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.autoevents.auto_events_client.feature.profile.ui.profile.Action
import ru.autoevents.auto_events_client.feature.profile.ui.profile.State

@Composable
fun ProfileContent(
    state: State,
    onAction: (Action) -> Unit
) {
    Text("Profile Screen Content")
}


