package ru.autoevents.auto_events_client.feature.home.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.autoevents.auto_events_client.core.network.api.EventApi
import ru.autoevents.auto_events_client.core.network.api.VenueApi
import ru.autoevents.auto_events_client.feature.home.domain.model.CityUi
import ru.autoevents.auto_events_client.feature.home.domain.model.EventTypeUi
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi

class EventRepository(private val eventApi: EventApi, private val venueApi: VenueApi) {
    fun fetchEvents(cityId: Int?, typeId: Int?): Flow<PagingData<EventUi>> {
        val config = PagingConfig(EventsPagingSource.PAGE_SIZE, EventsPagingSource.PAGE_PREFETCH)
        return Pager(config) { EventsPagingSource(eventApi, cityId, typeId) }.flow
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