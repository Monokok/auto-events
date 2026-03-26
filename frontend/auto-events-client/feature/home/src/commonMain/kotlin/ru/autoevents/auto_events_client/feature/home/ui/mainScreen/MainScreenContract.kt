package ru.autoevents.auto_events_client.feature.home.ui.mainScreen

import ru.autoevents.auto_events_client.core.ui.mvi.MviAction
import ru.autoevents.auto_events_client.core.ui.mvi.MviEffect
import ru.autoevents.auto_events_client.core.ui.mvi.MviState
import ru.autoevents.auto_events_client.feature.home.data.model.CityUi
import ru.autoevents.auto_events_client.feature.home.data.model.EventUi

sealed interface Effect : MviEffect

sealed interface Action : MviAction {
    data class GetEvents(val cityId: Int?) : Action
    data object Init : Action
}

data class State(
    val events: List<EventUi> = emptyList(),
    val cities: List<CityUi> = emptyList(),
    val loading: Boolean = false,
) : MviState