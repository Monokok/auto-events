package ru.autoevents.auto_events_client.feature.home.ui.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

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

    MainScreenContent(state = state, onAction = screenModel::pushAction)
}

@Composable
internal expect fun MainScreenContent(
    state: State,
    onAction: (Action) -> Unit
)