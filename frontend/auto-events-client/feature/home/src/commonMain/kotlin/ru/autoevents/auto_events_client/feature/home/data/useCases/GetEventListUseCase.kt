package ru.autoevents.auto_events_client.feature.home.data.useCases

import ru.autoevents.auto_events_client.feature.home.data.model.EventUi
import ru.autoevents.auto_events_client.feature.home.domain.EventRepository

class GetEventListUseCase(val repository: EventRepository) {
    suspend operator fun invoke(): Result<List<EventUi>> =
        repository.fetchEvents()
}