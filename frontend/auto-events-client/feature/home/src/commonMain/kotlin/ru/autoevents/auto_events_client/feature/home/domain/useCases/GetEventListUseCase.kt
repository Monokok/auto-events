package ru.autoevents.auto_events_client.feature.home.domain.useCases

import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi
import ru.autoevents.auto_events_client.feature.home.data.EventRepository

class GetEventListUseCase(val repository: EventRepository) {
    suspend operator fun invoke(cityId: Int? = null, typeId: Int? = null): Result<List<EventUi>> =
        repository.fetchEvents(cityId, typeId)
}