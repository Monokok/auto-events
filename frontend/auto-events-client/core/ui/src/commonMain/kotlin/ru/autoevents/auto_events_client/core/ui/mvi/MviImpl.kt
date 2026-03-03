package ru.autoevents.auto_events_client.core.ui.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MviImpl<Effect : MviEffect, Action : MviAction, State : MviState>(
    defaultState: State,
    val handleAction: suspend (Action) -> Unit,
    val scope: CoroutineScope,
): Mvi<Effect, Action, State>{
    private val _action: MutableSharedFlow<Action> = MutableSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    override val effect get() = _effect.receiveAsFlow()

    private val _state: MutableStateFlow<State> = MutableStateFlow(defaultState)
    override val state get() = _state.asStateFlow()

    init {
        subscribeEvents()
    }

    override fun pushAction(action: Action) {
        scope.launch {
            _action.emit(action)
        }
    }

    override fun pushEffect(builder: () -> Effect) {
        scope.launch {
            _effect.send(builder())
        }
    }

    override fun pushState(reduce: (State) -> State) {
        _state.update { reduce(it) }
    }

    private fun subscribeEvents() {
        scope.launch {
            _action.collect { handleAction(it) }
        }
    }

}