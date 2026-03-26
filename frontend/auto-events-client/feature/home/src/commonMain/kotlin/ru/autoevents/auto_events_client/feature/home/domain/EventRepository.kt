package ru.autoevents.auto_events_client.feature.home.domain

import ru.autoevents.auto_events_client.core.network.api.EventApi
import ru.autoevents.auto_events_client.core.network.api.VenueApi
import ru.autoevents.auto_events_client.feature.home.data.model.CityUi
import ru.autoevents.auto_events_client.feature.home.data.model.EventUi

class EventRepository(private val eventApi: EventApi, private val venueApi: VenueApi) {
    suspend fun fetchEvents(cityId: Int?): Result<List<EventUi>> = runCatching {
        eventApi.getEvents(cityId).mapToUi()
    }

    suspend fun fetchCities(): Result<List<CityUi>> = runCatching {
        venueApi.getCities().mapToUi()
    }
}