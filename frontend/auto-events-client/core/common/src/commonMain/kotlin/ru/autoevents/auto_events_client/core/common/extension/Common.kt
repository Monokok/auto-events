package ru.autoevents.auto_events_client.core.common.extension

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun String.toLocalDateTimeWithZone(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime? =
    runCatching { Instant.parse(this).toLocalDateTime(timeZone) }.getOrNull()