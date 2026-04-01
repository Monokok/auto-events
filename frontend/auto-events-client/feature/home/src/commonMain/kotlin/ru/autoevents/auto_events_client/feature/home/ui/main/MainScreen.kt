package ru.autoevents.auto_events_client.feature.home.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.koin.compose.koinInject
import ru.autoevents.auto_events_client.feature.home.ui.eventInfo.EventInfoScreen

/**
 * Экран главной страницы приложения.
 *
 * Использует [Screen] из Voyager для навигации и внедряет [MainScreenModel]
 * через Koin. Является точкой входа в UI главного экрана.
 */
class MainScreen : Screen {

    /**
     * Основной composable-контент экрана.
     *
     * Инициализирует [MainScreenModel] через DI и передаёт его в [MainScreen].
     */
    @Composable
    override fun Content() =
        MainScreen(
            screenModel = koinInject()
        )
}

/**
 * Контейнерный composable для главного экрана.
 *
 * Подписывается на [state] из [MainScreenModel] и прокидывает его в UI.
 * Также передаёт обработчик действий пользователя [onAction].
 *
 * @param screenModel модель экрана, содержащая бизнес-логику и состояние
 */
@Composable
private fun MainScreen(
    screenModel: MainScreenModel
) {
    val state by screenModel.state.collectAsState()
    val navigator = LocalNavigator.current

    MainScreenContent(
        state = state,
        onAction = screenModel::pushAction,
        navigateToEventInfo = {
            navigator?.push(EventInfoScreen(it))
        }
    )
}

/**
 * Платформенно-зависимая реализация UI главного экрана.
 *
 * Ожидается, что реализация будет предоставлена отдельно для каждой платформы
 * (например, Android/iOS) в KMP-проекте.
 *
 * @param state текущее состояние экрана
 * @param onAction обработчик пользовательских действий (интентов)
 */
@Composable
internal expect fun MainScreenContent(
    state: State,
    onAction: (Action) -> Unit,
    navigateToEventInfo: (Int) -> Unit,
)