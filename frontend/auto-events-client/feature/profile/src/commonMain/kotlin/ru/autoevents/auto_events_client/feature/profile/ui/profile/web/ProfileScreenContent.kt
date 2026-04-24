package ru.autoevents.auto_events_client.feature.profile.ui.profile.web

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.autoevents.auto_events_client.core.ui.components.LoaderFullScreen
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
            ){
                Text("Hello ${authState.user.username}")
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {onAction(Action.Logout)}
                ){
                    Text("Logout")
                }
            }
        }
        is AuthState.Unauthenticated -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                Text(text = "Вы не вошли в систему")
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = navigateLogin
                ){
                    Text("Войти")
                }
            }
        }
        else -> LoaderFullScreen(true)
    }
}


