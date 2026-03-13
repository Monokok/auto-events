package ru.autoevents.auto_events_client.feature.home.ui.mainScreen.web


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
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.components.LoaderFullScreen
import ru.autoevents.auto_events_client.core.ui.components.Screen
import ru.autoevents.auto_events_client.core.ui.components.WebPreview
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.*

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
@WebPreview
private fun WebScreenPreview() {
    MaterialTheme {
        ScreenContent(
            state = State(),
            onAction = {}
        )
    }
}