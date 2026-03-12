package ru.autoevents.auto_events_client.feature.home.data.model

import androidx.compose.runtime.Immutable
import kotlin.time.Instant

@Immutable
data class EventUi(
    val id: Int,
    val title: String,
    val description: String,
    val eventType: String,
    val region: String,
    val city: String,
    val venue: String,
    val startsAt: Instant?,
    val endsAt: Instant?,
    val isFree: Boolean,
    val ticketUrl: String?,
    val registrationUrl: String?,
    val status: String
)
