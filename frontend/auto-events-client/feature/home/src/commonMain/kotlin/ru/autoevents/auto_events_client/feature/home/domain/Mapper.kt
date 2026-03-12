package ru.autoevents.auto_events_client.feature.home.domain

import ru.autoevents.auto_events_client.feature.home.data.model.EventUi
import ru.autoevents.auto_events_client.feature.home.domain.entity.EventDto
import ru.autoevents.auto_events_client.feature.home.domain.entity.EventResponseDto
import kotlin.time.Instant

private fun EventDto.mapToUi(): EventUi = EventUi(
    id = id ?: 0,
    title = title.orEmpty(),
    description = description.orEmpty(),
    eventType = eventType.orEmpty(),
    region = region.orEmpty(),
    city = city.orEmpty(),
    venue = venue.orEmpty(),
    startsAt = startsAt?.let { runCatching { Instant.parse(it) }.getOrNull() },
    endsAt = endsAt?.let { runCatching { Instant.parse(it) }.getOrNull() },
    isFree = isFree ?: false,
    ticketUrl = ticketUrl,
    registrationUrl = registrationUrl,
    status = status.orEmpty()
)

fun EventResponseDto.mapToUi(): List<EventUi> =
    items?.map { it.mapToUi() } ?: emptyList()