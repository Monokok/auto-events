package ru.autoevents.auto_events_client.core.ui.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MviEffect

interface MviAction

interface MviState

interface Mvi<Effect : MviEffect, Action : MviAction, State : MviState> {
    val effect: Flow<Effect>
    val state: StateFlow<State>

    fun pushEffect(builder: () -> Effect)
    fun pushAction(action: Action)
    fun pushState(reduce: (State) -> State)
}

fun <Effect : MviEffect, Action : MviAction, State : MviState> createMvi(
    defaultState: State,
    handleAction: suspend (Action) -> Unit = {},
    scope: CoroutineScope,
): Mvi<Effect, Action, State> = MviImpl(
    defaultState = defaultState,
    handleAction = handleAction,
    scope = scope
)