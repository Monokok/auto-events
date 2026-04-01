package ru.autoevents.auto_events_client.feature.home.ui.eventInfo

import ru.autoevents.auto_events_client.core.ui.mvi.MviAction
import ru.autoevents.auto_events_client.core.ui.mvi.MviEffect
import ru.autoevents.auto_events_client.core.ui.mvi.MviState
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi

/**
 * Модель эффектов экрана событий.
 * Представляет одноразовые побочные эффекты, которые должны быть выполнены View
 * (навигация, тосты, диалоги и т.д.)
 */
sealed interface Effect : MviEffect

/**
 * Модель действий (интентов) пользователя на экране событий.
 * Представляет все возможные взаимодействия пользователя с UI.
 */
sealed interface Action : MviAction {
    data class GetEventInfo(val eventId: Int): Action
}

/**
 * Модель состояния экрана событий.
 * Содержит все данные, необходимые для отображения UI в конкретный момент времени.
 *

 * @property loading флаг, указывающий на процесс загрузки данных
 */
data class State(
    val eventUi: EventUi? = null,
    val loading: Boolean = false,
) : MviState
