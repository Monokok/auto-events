package ru.autoevents.auto_events_client.feature.home.ui.main

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi
import ru.autoevents.auto_events_client.feature.home.domain.useCases.GetCitiesUseCase
import ru.autoevents.auto_events_client.feature.home.domain.useCases.GetEventListUseCase
import ru.autoevents.auto_events_client.feature.home.domain.useCases.GetEventTypesListUseCase
import kotlin.time.Instant

class MainScreenModel(
    private val getEventListUseCase: GetEventListUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getEventTypesUseCase: GetEventTypesListUseCase,
) : MviScreenModel<Effect, Action, State>(
    defaultState = State(),
) {

    init {
        pushAction(Action.Init)
    }

    override suspend fun handleAction(action: Action) {
        when (action) {
            is Action.GetEvents -> getEvents(action.cityId)
            is Action.ChangeFilter -> getEventsByFilter(cityId = action.cityId, typeId = action.typeId)
            Action.Init -> initLoad()
            Action.GetEventTypes -> loadEventTypes()
        }
    }

    private fun initLoad() {
        screenModelScope.launch {
            pushState { it.copy(loading = true) }
            loadEvents()
            loadCities()
            loadEventTypes()
        }
    }

    /**
     * получение событий через API
     * @param [cityId] id города для фильтра. if null - по всем городам
     * @param [typeId] id типа для фильтра. if null - по всем событиям
     */
    private suspend fun loadEvents(cityId: Int? = null, typeId: Int? = null) {
        val resultEvents = getEventListUseCase.invoke(cityId = cityId, typeId = typeId)
        when {
            resultEvents.isSuccess -> {
                pushState {
                    it.copy(
                        loading = false,
                        events = resultEvents.getOrNull() ?: emptyList(),
                    )
                }
            }
        }
    }

    private suspend fun loadCities() {
        val resultEvents = getCitiesUseCase.invoke()
        when {
            resultEvents.isSuccess -> {
                pushState {
                    it.copy(
                        cities = resultEvents.getOrNull() ?: emptyList(),
                    )
                }
            }
        }
    }

    private fun getEvents(cityId: Int?) {
        screenModelScope.launch {
            pushState { it.copy(loading = true) }
            val result = getEventListUseCase.invoke(cityId)
            when {
                result.isSuccess -> {
                    pushState {
                        it.copy(
                            loading = false,
                            events = result.getOrNull() ?: emptyList(),
                        )
                    }
                }
            }
        }
    }

    /**
     * Метод для получения списка типов событий (дрифт, гонка и пр.)
     */
    private fun loadEventTypes() {
        screenModelScope.launch {
            pushState { it.copy(loading = true) }
            val result = getEventTypesUseCase.invoke()
            when {
                result.isSuccess -> {
                    pushState {
                        it.copy(
                            loading = false,
                            eventTypes = result.getOrNull() ?: emptyList(),
                        )
                    }
                }
            }
        }
    }

    private fun getEventsByFilter(cityId: Int?, typeId: Int?) {
        screenModelScope.launch {
            // обновляем выбранный тип - перерисовка интерфейса
            pushState {
                state ->
                state.copy(
                    selectedCityId = cityId,
                    selectedEventTypeId = typeId,
                    eventTypes = state.eventTypes.map{
                        it.copy(
                            isSelected = it.id == typeId
                        )
                    }
                )
            }

            //загружаем события с фильтром
            loadEvents(cityId = cityId, typeId = typeId)
        }
    }
}

private val mockedList = listOf(
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        pictureUrl = null,
        status = "published"
    ),
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        pictureUrl = null,
        status = "published"
    ),
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        pictureUrl = null,
        status = "published"
    ),
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        pictureUrl = null,
        status = "published"
    ),
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        pictureUrl = null,
        status = "published"
    ),
)