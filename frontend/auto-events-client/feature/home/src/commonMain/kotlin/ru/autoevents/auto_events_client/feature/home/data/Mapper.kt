package ru.autoevents.auto_events_client.feature.home.data

import ru.autoevents.auto_events_client.core.common.extension.toLocalDateTimeWithZone
import ru.autoevents.auto_events_client.core.network.entity.CityDto
import ru.autoevents.auto_events_client.core.network.entity.EventDto
import ru.autoevents.auto_events_client.core.network.entity.EventResponseDto
import ru.autoevents.auto_events_client.core.network.entity.EventTypeResponseDto
import ru.autoevents.auto_events_client.feature.home.domain.model.CityUi
import ru.autoevents.auto_events_client.feature.home.domain.model.EventTypeUi
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi
import kotlin.coroutines.EmptyCoroutineContext.get
import kotlin.jvm.JvmName

fun EventDto.mapToUi(): EventUi = EventUi(
    id = id ?: 0,
    title = title.orEmpty(),
    description = description.orEmpty(),
    eventType = eventType.orEmpty(),
    region = region.orEmpty(),
    city = city.orEmpty(),
    venue = venue.orEmpty(),
    startsAt = startsAt?.toLocalDateTimeWithZone(),
    endsAt = endsAt?.toLocalDateTimeWithZone(),
    isFree = isFree ?: false,
    ticketUrl = ticketUrl,
    registrationUrl = registrationUrl,
    status = status.orEmpty(),
    pictureUrl = pictureUrl,
    viewsCount = viewsCount,
)

fun EventUi.getViewsCountString(): String {
    return if (viewsCount == null) "0"
    else formatViews(viewsCount)
}

fun formatViews(count: Int): String {
    return when {
        count < 1000 -> count.toString()

        count < 10_000 -> {
            val value = count / 1000
            val decimal = (count % 1000) / 100  // одна цифра после запятой

            if (decimal == 0) "${value}K"
            else "${value}.${decimal}K"
        }

        count < 1_000_000 -> {
            "${count / 1000}K"
        }

        else -> {
            val value = count / 1_000_000
            val decimal = (count % 1_000_000) / 100_000

            if (decimal == 0) "${value}M"
            else "${value}.${decimal}M"
        }
    }
}

fun EventResponseDto.mapToUi(): List<EventUi> =
    items?.map { it.mapToUi() } ?: emptyList()

private fun CityDto.mapToUi(): CityUi = CityUi(
    id = id ?: 0,
    name = name.orEmpty(),
)

@JvmName("mapCityListToUi")         //fix конфликта сигнатур на уровне JVM
fun List<CityDto>?.mapToUi(): List<CityUi> =
    this?.map { it.mapToUi() } ?: emptyList()

private fun EventTypeResponseDto.mapToUi(): EventTypeUi = EventTypeUi(
    id = id ?: 0,
    name = name.orEmpty(),
)

@JvmName("mapEventTypeListToUi")    //fix конфликта сигнатур на уровне JVM
fun List<EventTypeResponseDto>?.mapToUi(): List<EventTypeUi> =
    this?.map { it.mapToUi() } ?: emptyList()