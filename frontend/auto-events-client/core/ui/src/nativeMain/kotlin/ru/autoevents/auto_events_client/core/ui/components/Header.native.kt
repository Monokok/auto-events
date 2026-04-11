package ru.autoevents.auto_events_client.core.ui.components

import androidx.compose.runtime.Composable

@Composable
actual fun Header(
    locationContent: @Composable () -> Unit,
    navigationContent: @Composable () -> Unit,
    navigateBack: (() -> Unit)?
) {
    MobileHeader(
        locationContent,
        navigationContent,
        navigateBack
    )
}