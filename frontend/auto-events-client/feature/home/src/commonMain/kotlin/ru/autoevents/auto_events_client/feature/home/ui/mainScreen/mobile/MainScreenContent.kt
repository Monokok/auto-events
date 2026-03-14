package ru.autoevents.auto_events_client.feature.home.ui.mainScreen.mobile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import auto_events_client.feature.home.generated.resources.Res
import auto_events_client.feature.home.generated.resources.all_events
import auto_events_client.feature.home.generated.resources.nearest_events
import auto_events_client.feature.home.generated.resources.popular_events
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.components.LoaderFullScreen
import ru.autoevents.auto_events_client.core.ui.components.MobilePreview
import ru.autoevents.auto_events_client.core.ui.components.Screen
import ru.autoevents.auto_events_client.feature.home.data.model.EventUi
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.*
import kotlin.time.Instant

@Composable
internal fun ScreenContent(
    state: State,
    onAction: (Action) -> Unit
) {
    Screen {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
        ) {
            item {
                ChapterRow(
                    chapterName = stringResource(Res.string.nearest_events),
                    allText = stringResource(Res.string.all_events),
                )
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                ) {
                    items(state.events) { event ->
                        EventCard(event)
                    }
                }
            }
            item {
                ChapterRow(
                    chapterName = stringResource(Res.string.popular_events),
                    allText = stringResource(Res.string.all_events),
                )
            }
            items(state.events) {
                EventRow(
                    event = it,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            }
        }
        LoaderFullScreen(state.loading)
    }
}

@Composable
@MobilePreview
private fun MobileScreenPreview() {
    MaterialTheme {
        ScreenContent(
            state = State(
                events = mockedList
            ),
            onAction = {}
        )
    }
}

private val mockedList = listOf(
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        status = "published"
    ),
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        status = "published"
    ),
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        status = "published"
    ),
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        status = "published"
    ),
    EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов",
        eventType = "drift",
        region = "Московская область",
        city = "Москва",
        venue = "Автодром Moscow Raceway",
        startsAt = Instant.parse("2026-03-12T18:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        endsAt = Instant.parse("2026-03-12T22:00:00Z").toLocalDateTime(TimeZone.currentSystemDefault()),
        isFree = false,
        ticketUrl = "https://example.com/tickets/1",
        registrationUrl = null,
        status = "published"
    ),
)