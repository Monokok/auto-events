package ru.autoevents.auto_events_client.core.ui.mvi

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//fun ScreenModel.launch(block: suspend CoroutineScope.() -> Unit) =
//    screenModelScope.launch { block() }

//@Composable
//fun <E : MviEffect> MviEffectResolver(
//    flow: Flow<E>,
//    block: (E) -> Unit,
//) {
//    val lifecycle = LocalLifecycleOwner.current
//    LaunchedEffect(flow) {
//        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
//            flow.collect { block(it) }
//        }
//    }
//}