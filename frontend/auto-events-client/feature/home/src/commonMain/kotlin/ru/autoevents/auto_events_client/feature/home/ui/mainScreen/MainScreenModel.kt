package ru.autoevents.auto_events_client.feature.home.ui.mainScreen

import ru.autoevents.auto_events_client.core.ui.mvi.MviScreenModel

class MainScreenModel : MviScreenModel<Effect, Action, State>(
    defaultState = State()
) {

    override suspend fun handleAction(action: Action) {
        when (action) {
            is Action.SetShowContent -> pushState { it.copy(showContent = action.value) }
        }
    }
}