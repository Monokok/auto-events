package ru.autoevents.auto_events_client.feature.home.data

import ru.autoevents.auto_events_client.core.network.api.EventApi
import ru.autoevents.auto_events_client.core.network.api.VenueApi
import ru.autoevents.auto_events_client.feature.home.domain.model.CityUi
import ru.autoevents.auto_events_client.feature.home.domain.model.EventTypeUi
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi

class EventRepository(private val eventApi: EventApi, private val venueApi: VenueApi) {
    suspend fun fetchEvents(cityId: Int?, typeId: Int?): Result<List<EventUi>> = runCatching {
        eventApi.getEvents(cityId, typeId).mapToUi()
    }

    suspend fun fetchCities(): Result<List<CityUi>> = runCatching {
        venueApi.getCities().mapToUi()
    }

    suspend fun fetchEventTypes(): Result<List<EventTypeUi>> = runCatching {
        eventApi.getEventsTypes().mapToUi()
    }

    suspend fun fetchEventInfo(eventId: Int): Result<EventUi> = runCatching {
        eventApi.getEvent(eventId).mapToUi()
    }
}