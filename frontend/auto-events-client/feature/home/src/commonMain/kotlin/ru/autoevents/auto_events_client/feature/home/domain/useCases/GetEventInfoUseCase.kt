package ru.autoevents.auto_events_client.feature.home.domain.useCases

import ru.autoevents.auto_events_client.feature.home.data.EventRepository
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi

class GetEventInfoUseCase(val repository: EventRepository) {
    suspend operator fun invoke(eventId: Int): Result<EventUi> =
        repository.fetchEventInfo(eventId)
}