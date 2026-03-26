package ru.autoevents.auto_events_client.feature.home.ui.eventScreen

import ru.autoevents.auto_events_client.feature.home.data.model.CityUi
import ru.autoevents.auto_events_client.core.ui.mvi.MviAction
import ru.autoevents.auto_events_client.core.ui.mvi.MviEffect
import ru.autoevents.auto_events_client.core.ui.mvi.MviState

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
    /**
     * Запрос на загрузку списка городов.
     * Вызывается при открытии экрана или при свайпе для обновления.
     */
    data object GetCities : Action

    /**
     * Действие выбора города пользователем.
     *
     * @param city выбранный пользователем город
     */
    data class SelectCity(val city: CityUi) : Action

    /**
     * Действие отмена выбора города пользователем.
     *
     * @param city отмененный пользователем город
     */
    data class DeleteCity(val city: CityUi) : Action
}

/**
 * Модель состояния экрана событий.
 * Содержит все данные, необходимые для отображения UI в конкретный момент времени.
 *
 * @property cities список доступных городов для отображения
 * @property selectedCity текущий выбранный город (null если ничего не выбрано)
 * @property loading флаг, указывающий на процесс загрузки данных
 */
data class State(
    val cities: List<CityUi> = emptyList(),
    val selectedCity: CityUi? = null,
    val loading: Boolean = false,
) : MviState
