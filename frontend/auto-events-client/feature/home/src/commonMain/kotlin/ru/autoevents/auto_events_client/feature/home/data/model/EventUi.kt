package ru.autoevents.auto_events_client.feature.home.data.model

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDateTime

@Immutable
data class EventUi(
    val id: Int,
    val title: String,
    val description: String,
    val eventType: String,
    val region: String,
    val city: String,
    val venue: String,
    val startsAt: LocalDateTime?,
    val endsAt: LocalDateTime?,
    val isFree: Boolean,
    val ticketUrl: String?,
    val registrationUrl: String?,
    val status: String
)
