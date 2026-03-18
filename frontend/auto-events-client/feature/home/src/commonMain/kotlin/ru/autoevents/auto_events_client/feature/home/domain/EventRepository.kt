package ru.autoevents.auto_events_client.feature.home.domain

import ru.autoevents.auto_events_client.core.network.api.EventApi
import ru.autoevents.auto_events_client.feature.home.data.model.EventUi

/**
 * Репозиторий автомобильных событий
 *
 * @property api объект для получения событий через запрос к нему
 * @return  [List]<[EventUi]>
 */
class EventRepository(private val api: EventApi) {
    suspend fun fetchEvents(): Result<List<EventUi>> = runCatching {
        api.getEvents().mapToUi()
    }
}