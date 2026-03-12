package ru.autoevents.auto_events_client.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.autoevents.auto_events_client.core.ui.theme.white900

@Composable
fun Screen(
    content: @Composable () -> Unit,
) {
    Scaffold(
        content = { padding ->
            Box(
                content = { content() },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(WindowInsets.ime)
            )
        },
        containerColor = MaterialTheme.colorScheme.white900,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.white900)
            .navigationBarsPadding()
            .background(MaterialTheme.colorScheme.white900)
            .statusBarsPadding()
    )
}