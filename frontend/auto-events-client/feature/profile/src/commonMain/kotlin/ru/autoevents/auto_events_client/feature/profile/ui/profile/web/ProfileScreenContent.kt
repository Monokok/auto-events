package ru.autoevents.auto_events_client.feature.profile.ui.profile.web

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.autoevents.auto_events_client.core.ui.components.LoaderFullScreen
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal
import ru.autoevents.auto_events_client.core.ui.theme.inter16Bold
import ru.autoevents.auto_events_client.feature.profile.ui.profile.Action
import ru.autoevents.auto_events_client.feature.profile.ui.profile.AuthState
import ru.autoevents.auto_events_client.feature.profile.ui.profile.State

@Composable
fun ProfileContent(
    state: State,
    onAction: (Action) -> Unit,
    navigateLogin: () -> Unit,
) {
    when (val authState = state.authState) {
        is AuthState.Authenticated -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Hello ${authState.user.username}",
                    style = MaterialTheme.typography.inter16Bold,
                    color = MaterialTheme.colorScheme.dark900,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { onAction(Action.Logout) }
                ) {
                    Text(
                        text = "Logout",
                        style = MaterialTheme.typography.inter16Bold,
                        color = MaterialTheme.colorScheme.dark900,
                    )
                }
            }
        }

        is AuthState.Unauthenticated -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Вы не вошли в систему",
                    style = MaterialTheme.typography.inter16Bold,
                    color = MaterialTheme.colorScheme.dark900,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = navigateLogin
                ) {
                    Text(
                        text = "Войти",
                        style = MaterialTheme.typography.inter14Normal,
                        color = MaterialTheme.colorScheme.dark900,
                    )
                }
            }
        }

        else -> LoaderFullScreen(true)
    }
}


