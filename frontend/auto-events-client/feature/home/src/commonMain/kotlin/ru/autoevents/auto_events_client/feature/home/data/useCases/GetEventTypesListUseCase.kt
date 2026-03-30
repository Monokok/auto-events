package ru.autoevents.auto_events_client.feature.home.data.useCases

import ru.autoevents.auto_events_client.feature.home.data.model.EventTypeUi
import ru.autoevents.auto_events_client.feature.home.domain.EventRepository

class GetEventTypesListUseCase(var repository: EventRepository) {
    suspend operator fun invoke(): Result<List<EventTypeUi>> = repository.fetchEventTypes()
}