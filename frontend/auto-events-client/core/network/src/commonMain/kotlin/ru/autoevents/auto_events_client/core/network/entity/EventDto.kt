package ru.autoevents.auto_events_client.core.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventResponseDto(
    @SerialName("items")
    val items: List<EventDto>? = null,
    @SerialName("total")
    val total: Int? = null
)

@Serializable
data class EventDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("event_type")
    val eventType: String? = null,
    @SerialName("region")
    val region: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("venue")
    val venue: String? = null,
    @SerialName("starts_at")
    val startsAt: String? = null,   // строка в формате ISO 8601
    @SerialName("ends_at")
    val endsAt: String? = null,     // строка в формате ISO 8601
    @SerialName("is_free")
    val isFree: Boolean? = null,
    @SerialName("ticket_url")
    val ticketUrl: String? = null,
    @SerialName("registration_url")
    val registrationUrl: String? = null,
    @SerialName("status")
    val status: String? = null
)