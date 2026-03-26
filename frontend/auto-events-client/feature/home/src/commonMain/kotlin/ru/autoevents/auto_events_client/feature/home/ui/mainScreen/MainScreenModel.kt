package ru.autoevents.auto_events_client.feature.home.ui.mainScreen

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel
import ru.autoevents.auto_events_client.feature.home.data.model.EventUi
import ru.autoevents.auto_events_client.feature.home.data.useCases.GetCitiesUseCase
import ru.autoevents.auto_events_client.feature.home.data.useCases.GetEventListUseCase
import kotlin.time.Instant

class MainScreenModel(
    private val getEventListUseCase: GetEventListUseCase,
    private val getCitiesUseCase: GetCitiesUseCase,
) : MviScreenModel<Effect, Action, State>(
    defaultState = State(),
) {

    init {
        pushAction(Action.Init)
    }

    override suspend fun handleAction(action: Action) {
        when (action) {
            is Action.GetEvents -> getEvents(action.cityId)
            Action.Init -> initLoad()
        }
    }

    private fun initLoad() {
        screenModelScope.launch {
            pushState { it.copy(loading = true) }
            loadEvents()
            loadCities()
        }
    }

    private suspend fun loadEvents() {
        val resultEvents = getEventListUseCase.invoke()
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
        status = "published"
    ),
)