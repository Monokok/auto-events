package ru.autoevents.auto_events_client.feature.home.ui.eventInfo

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel
import ru.autoevents.auto_events_client.feature.home.domain.useCases.GetEventInfoUseCase

class EventInfoScreenModel(
    private val getEventInfoUseCase: GetEventInfoUseCase,
) : MviScreenModel<Effect, Action, State>(
    defaultState = State(),
) {

    override suspend fun handleAction(action: Action) {
        when (action) {
            is Action.GetEventInfo -> getEventInfo(action.eventId)
        }
    }

    private fun getEventInfo(id: Int) {
        screenModelScope.launch {
            pushState { it.copy(loading = true) }
            val result = getEventInfoUseCase(id)
            when {
                result.isSuccess -> {
                    pushState { it.copy(loading = false, eventUi = result.getOrNull()) }
                }
            }
        }
    }
}