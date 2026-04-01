package ru.autoevents.auto_events_client.feature.home.ui.eventInfo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.koinInject

/**
 * Экран главной страницы приложения.
 *
 * Использует [Screen] из Voyager для навигации и внедряет [EventInfoScreenModel]
 * через Koin. Является точкой входа в UI главного экрана.
 */
class EventInfoScreen(val eventId: Int) : Screen {

    /**
     * Основной composable-контент экрана.
     *
     * Инициализирует [EventInfoScreenModel] через DI и передаёт его в [EventScreen].
     */
    @Composable
    override fun Content() =
        EventInfoScreen(
            eventId = eventId,
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
private fun EventInfoScreen(
    eventId: Int,
    screenModel: EventInfoScreenModel,
) {
    val state by screenModel.state.collectAsState()

    LaunchedEffect(eventId) {
        screenModel.pushAction(Action.GetEventInfo(eventId))
    }

    EventInfoScreenContent(
        state = state,
        onAction = screenModel::pushAction
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
internal expect fun EventInfoScreenContent(
    state: State,
    onAction: (Action) -> Unit
)