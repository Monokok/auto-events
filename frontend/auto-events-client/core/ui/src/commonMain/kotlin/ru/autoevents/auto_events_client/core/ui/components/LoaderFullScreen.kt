package ru.autoevents.auto_events_client.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.autoevents.auto_events_client.core.ui.theme.white950

@Composable
fun LoaderFullScreen(visible: Boolean) {
    AnimatedVisibility(visible = visible) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.white950),
        ) {
            CircularProgressIndicator()
        }
    }
}