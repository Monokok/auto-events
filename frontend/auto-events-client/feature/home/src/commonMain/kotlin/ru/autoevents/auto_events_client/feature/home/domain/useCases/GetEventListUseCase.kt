package ru.autoevents.auto_events_client.feature.home.domain.useCases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.autoevents.auto_events_client.feature.home.data.EventRepository
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi

class GetEventListUseCase(val repository: EventRepository) {
    operator fun invoke(cityId: Int? = null, typeId: Int? = null): Flow<PagingData<EventUi>> =
        repository.fetchEvents(cityId, typeId)
}