package ru.autoevents.auto_events_client.core.ui.mvi

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

abstract class MviScreenModel<Effect : MviEffect, Action : MviAction, State : MviState>(
    defaultState: State
) : ScreenModel, Mvi<Effect, Action, State> {
    private val mvi: Mvi<Effect, Action, State> by lazy {
        createMvi(
            defaultState = defaultState,
            scope = screenModelScope,
            handleAction = ::handleAction
        )
    }

    final override val effect: Flow<Effect> get() = mvi.effect
    final override val state: StateFlow<State> get() = mvi.state

    final override fun pushAction(action: Action) = mvi.pushAction(action)
    final override fun pushState(reduce: (State) -> State) = mvi.pushState(reduce)
    final override fun pushEffect(builder: () -> Effect) = mvi.pushEffect(builder)

    protected abstract suspend fun handleAction(action: Action)
}