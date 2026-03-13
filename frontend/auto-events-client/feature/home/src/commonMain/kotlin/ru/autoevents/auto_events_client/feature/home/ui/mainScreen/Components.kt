package ru.autoevents.auto_events_client.feature.home.ui.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.feature.home.generated.resources.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.theme.*
import ru.autoevents.auto_events_client.feature.home.data.model.EventUi
import kotlin.time.Instant

@Composable
fun EventCard(
    event: EventUi,
) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Min)
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .shadow(
                    elevation = 10.dp,
                    shape = MaterialTheme.shapes.cardRadius10,
                    spotColor = MaterialTheme.colorScheme.primary900,
                )
                .padding(bottom = 5.dp)
                .clip(MaterialTheme.shapes.cardRadius10)
                .background(MaterialTheme.colorScheme.white900)
        ) {
            Image(
                painter = painterResource(Res.drawable.image_event_placeholder),
                contentScale = ContentScale.Crop,
                contentDescription = "event",
                modifier = Modifier.width(267.dp).height(184.dp)
            )
            event.startsAt?.let { date ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.cardRadius10)
                        .background(MaterialTheme.colorScheme.white950)
                        .padding(8.dp, 4.5.dp)
                ) {
                    Text(
                        text = date.month.name,
                        style = MaterialTheme.typography.inter10Normal,
                        color = MaterialTheme.colorScheme.dark900,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = date.day.toString(),
                        style = MaterialTheme.typography.inter14Bold,
                        color = MaterialTheme.colorScheme.dark900,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.inter14Bold,
                color = MaterialTheme.colorScheme.dark900,
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.inter14Bold,
                color = MaterialTheme.colorScheme.primary900,
            )
            Text(
                text = stringResource(if (event.isFree) Res.string.free else Res.string.paid_for),
                style = MaterialTheme.typography.inter14Normal,
                color = MaterialTheme.colorScheme.primary900,
            )
            Text(
                text = event.venue,
                style = MaterialTheme.typography.inter14Normal,
                color = MaterialTheme.colorScheme.dark700,
            )
        }
    }
}

@Composable
fun ChapterRow(
    chapterName: String,
    allText: String,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
    ) {
        Text(
            text = chapterName,
            style = MaterialTheme.typography.inter16Bold,
            color = MaterialTheme.colorScheme.dark900,
        )
        Text(
            text = allText,
            style = MaterialTheme.typography.inter14Normal,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
fun EventRow(
    event: EventUi,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.cardRadius16,
                spotColor = MaterialTheme.colorScheme.dark700
            )
            .clip(MaterialTheme.shapes.cardRadius16)
            .background(MaterialTheme.colorScheme.white900)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.image_event_placeholder2),
            contentScale = ContentScale.Crop,
            contentDescription = "event",
            modifier = Modifier.size(78.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = event.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.inter14Bold,
                color = MaterialTheme.colorScheme.dark900,
            )
            Text(
                text = stringResource(if (event.isFree) Res.string.free else Res.string.paid_for),
                style = MaterialTheme.typography.inter14Bold,
                color = MaterialTheme.colorScheme.primary900,
            )
            Text(
                text = event.venue,
                style = MaterialTheme.typography.inter14Normal,
                color = MaterialTheme.colorScheme.dark700,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
        }
        Spacer(modifier = Modifier.weight(1f).defaultMinSize(4.dp))
        Icon(
            painter = painterResource(Res.drawable.ic_heart_outlined),
            contentDescription = "favourite",
            tint = MaterialTheme.colorScheme.dark700,
            modifier = Modifier.clip(CircleShape).clickable {}.padding(12.dp).size(24.dp)
        )
    }
}

@Composable
@Preview
private fun EventCardPreview() {
    MaterialTheme {
        EventCard(
            event = previewEvent
        )
    }
}

val previewEvent = EventUi(
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
)