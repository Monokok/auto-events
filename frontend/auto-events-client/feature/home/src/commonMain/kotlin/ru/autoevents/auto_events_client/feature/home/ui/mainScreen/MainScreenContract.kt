package ru.autoevents.auto_events_client.feature.home.ui.mainScreen

import ru.autoevents.auto_events_client.core.ui.mvi.MviAction
import ru.autoevents.auto_events_client.core.ui.mvi.MviEffect
import ru.autoevents.auto_events_client.core.ui.mvi.MviState

sealed interface Effect : MviEffect

sealed interface Action : MviAction {
    data class SetShowContent(val value: Boolean) : Action
}

data class State(
    val showContent: Boolean = false,
) : MviState