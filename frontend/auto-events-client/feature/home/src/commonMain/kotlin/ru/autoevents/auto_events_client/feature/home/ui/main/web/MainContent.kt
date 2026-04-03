package ru.autoevents.auto_events_client.feature.home.ui.main.web


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
import ru.autoevents.auto_events_client.core.ui.components.WebPreview
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi
import ru.autoevents.auto_events_client.feature.home.ui.main.*
import ru.autoevents.auto_events_client.feature.home.ui.main.mobile.MainContent
import kotlin.time.Instant

@Composable
internal fun MainContent(
    state: State,
    onAction: (Action) -> Unit,
    navigateToEventInfo: (Int) -> Unit,
) {
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
                contentPadding = PaddingValues(horizontal = 8.dp),
            ) {
                items(state.events) { event ->
                    EventCard(event = event, onClick = navigateToEventInfo)
                }
            }
        }
        item {
            EventTypesBar(
                eventTypes = state.eventTypes,
                onEventTypeClick = { type ->
                    onAction(
                        Action.ChangeFilter(cityId = state.selectedCityId, typeId = type.id)
                    )
                },
                clearEventTypes = {
                    onAction(Action.ChangeFilter(cityId = state.selectedCityId, typeId = null))
                },
            )
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
                onClick = navigateToEventInfo,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
    LoaderFullScreen(state.loading)
}

@Composable
@WebPreview
private fun WebScreenPreview() {
    MaterialTheme {
        MainContent(
            state = State(
                events = mockedList
            ),
            onAction = {},
            navigateToEventInfo = {},
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
        pictureUrl = null,
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
        pictureUrl = null,
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
        pictureUrl = null,
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
        pictureUrl = null,
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
        pictureUrl = null,
        status = "published"
    ),
)