package ru.autoevents.auto_events_client.feature.login.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.compose.koinInject
import ru.autoevents.auto_events_client.core.ui.components.Header
import ru.autoevents.auto_events_client.core.ui.components.WebPreview
import ru.autoevents.auto_events_client.core.ui.components.Screen as AppScreen

/**
 * Экран регистрации пользователя.
 *
 * Использует [Screen] из Voyager для навигации и внедряет [LoginScreenModel]
 * через Koin. Является точкой входа в UI экрана регистрации.
 */
class LoginScreen : Screen {

    /**
     * Основной composable-контент экрана.
     *
     * Инициализирует [LoginScreenModel] через DI и передаёт его в [LoginScreen].
     */
    @Composable
    override fun Content() =
        LoginScreen(
            screenModel = koinInject()
        )
}

/**
 * Контейнерный composable для экрана регистрации.
 *
 * Подписывается на [state] из [LoginScreenModel] и прокидывает его в UI.
 * Также передаёт обработчик действий пользователя [onAction].
 *
 * @param screenModel модель экрана, содержащая бизнес-логику и состояние
 */
@Composable
private fun LoginScreen(
    screenModel: LoginScreenModel
) {
    val state by screenModel.state.collectAsState()
    val navigator = LocalNavigator.current

    AppScreen(
        topBar = {
            Header(
                navigateBack = {
                    navigator?.pop()
                }
            )
        }
    ) {
        LoginScreenContent(
            state = state,
            onAction = screenModel::pushAction
        )
    }
}

/**
 * Платформенно-зависимая реализация UI экрана регистрации.
 *
 * Ожидается, что реализация будет предоставлена отдельно для каждой платформы
 * (например, Android/iOS/Web) в KMP-проекте.
 *
 * @param state текущее состояние экрана
 * @param onAction обработчик пользовательских действий (интентов)
 */
@Composable
internal expect fun LoginScreenContent(
    state: State,
    onAction: (Action) -> Unit
)

@Composable
@WebPreview
fun ScreenContentPreview() {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoginScreen()
        }
    }
}
