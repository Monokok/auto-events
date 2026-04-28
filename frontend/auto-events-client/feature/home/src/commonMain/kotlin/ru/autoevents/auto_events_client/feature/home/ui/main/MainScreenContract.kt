package ru.autoevents.auto_events_client.feature.home.ui.main

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.autoevents.auto_events_client.core.ui.mvi.MviAction
import ru.autoevents.auto_events_client.core.ui.mvi.MviEffect
import ru.autoevents.auto_events_client.core.ui.mvi.MviState
import ru.autoevents.auto_events_client.feature.home.domain.model.CityUi
import ru.autoevents.auto_events_client.feature.home.domain.model.EventTypeUi
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi

sealed interface Effect : MviEffect


/**
 * [ChangeFilter] действие "Выбор типа события". [cityId] is nullable, т.к. можем применять фильтр с учетом всех городов
 */
sealed interface Action : MviAction {
    data class GetEvents(val cityId: Int?, val typeId: Int? = null) : Action
    data object GetEventTypes : Action
    data object Init : Action
    data class ChangeFilter(val cityId: Int?, val typeId: Int?) : Action
}

/**
 * @param [events] список автомобильных событий
 * @param [cities] список городов, по которым возможно получение событий
 * @param [selectedEventTypeId] выбранный тип событий для их фильтрованного получения через api. null = “Все”
 * @param [eventTypes] список типов, которые можно получить через api
 */
data class State(
    val events: Flow<PagingData<EventUi>> = flowOf(PagingData.empty()),
    val cities: List<CityUi> = emptyList(),
    val eventTypes: List<EventTypeUi> = emptyList(),
    val selectedEventTypeId: Int? = null,   //TODO: изменить на список
    val selectedCityId: Int? = null,
    val loading: Boolean = false,
) : MviState