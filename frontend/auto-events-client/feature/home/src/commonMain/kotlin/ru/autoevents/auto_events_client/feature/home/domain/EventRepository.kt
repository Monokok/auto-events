package ru.autoevents.auto_events_client.feature.home.domain

import ru.autoevents.auto_events_client.core.network.api.EventApi
import ru.autoevents.auto_events_client.feature.home.data.model.EventUi

class EventRepository(private val api: EventApi) {
    suspend fun fetchEvents(): Result<List<EventUi>> = runCatching {
        api.getEvents().mapToUi()
    }
}