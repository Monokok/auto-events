package ru.autoevents.auto_events_client.feature.home.ui.mainScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import auto_events_client.feature.home.generated.resources.Res
import auto_events_client.feature.home.generated.resources.compose_multiplatform
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import ru.autoevents.auto_events_client.core.common.greeting.Greeting

class MainScreen : Screen {

    @Composable
    override fun Content() =
        MainScreen(
            screenModel = koinInject()
        )
}

@Composable
private fun MainScreen(
    screenModel: MainScreenModel
) {
    val state by screenModel.state.collectAsState()

    ScreenContent(
        state = state,
        onAction = screenModel::pushAction,
    )
}

@Composable
private fun ScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { onAction(Action.SetShowContent(!state.showContent)) }) {
            Text("Click me!")
        }
        AnimatedVisibility(state.showContent) {
            val greeting = remember { Greeting().greet() }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}