package ru.autoevents.auto_events_client.feature.home.ui.eventInfo.mobile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.*
import auto_events_client.feature.home.generated.resources.*
import auto_events_client.feature.home.generated.resources.Res
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.components.LoaderFullScreen
import ru.autoevents.auto_events_client.core.ui.components.MobilePreview
import ru.autoevents.auto_events_client.core.ui.components.Screen
import ru.autoevents.auto_events_client.core.ui.models.LocalImageLoader
import ru.autoevents.auto_events_client.core.ui.theme.*
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi
import ru.autoevents.auto_events_client.feature.home.ui.eventInfo.State
import auto_events_client.core.ui.generated.resources.Res as uiRes


@Composable
internal fun EventInfoContent(state: State) {
    Screen(topBar = {}, bottomBar = {}) {
        EventBody(state)
    }
}

@Composable
private fun EventBody(state: State) {
    state.eventUi?.let {
        EventBodyContent(event = it)
    }

    LoaderFullScreen(state.loading)
}

@Composable
private fun EventBodyContent(
    event: EventUi
) {
    Box(Modifier.fillMaxSize()) {
        AsyncImage(
            model = event.pictureUrl,
            imageLoader = LocalImageLoader.current,
            contentDescription = event.pictureUrl,
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(Res.drawable.image_event_placeholder),
            modifier = Modifier.fillMaxWidth()
        )
    }
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                Brush.verticalGradient(
                    startY = 200f,
                    colors = listOf(
                        Color.Transparent,
                        MaterialTheme.colorScheme.white950
                    )
                )
            )
        ){
            Text(
                text = event.eventType,
                color = MaterialTheme.colorScheme.white950,
                style = MaterialTheme.typography.inter14Normal,
                modifier = Modifier
                    .padding(start = 16.dp, top = 245.dp)
                    .background(MaterialTheme.colorScheme.primary900, CircleShape)
                    .padding(vertical = 6.dp, horizontal = 11.dp)
            )
        }
        Column(
                Modifier
                    .background(MaterialTheme.colorScheme.white950)
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 32.dp),
        ) {
        Text(
            text = event.title,
            color = MaterialTheme.colorScheme.dark900,
            style = MaterialTheme.typography.inter20Bold,
        )
        Spacer(Modifier.height(16.dp))
        InfoRow(event)
        Spacer(Modifier.height(16.dp))
        OrganizerRow(
            name = "ФАС Иваново", //todo: подставлять настоящее название когда появится
            viewCount = event.viewsCount
        )
        Spacer(Modifier.height(16.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(Res.string.about_event),
                style = MaterialTheme.typography.inter14Bold,
                color = MaterialTheme.colorScheme.dark900,
            )
            Text(
                text = event.description,
                style = MaterialTheme.typography.inter14Normal,
                color = MaterialTheme.colorScheme.dark700,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = stringResource(uiRes.string.more_detailed),
                style = MaterialTheme.typography.inter14Link,
                color = MaterialTheme.colorScheme.primary900,
                modifier = Modifier.clickable {
                    TODO("Переход по ссылке")
                }
            )
        }
        Spacer(Modifier.height(24.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(Res.string.location),
                style = MaterialTheme.typography.inter14Bold,
                color = MaterialTheme.colorScheme.dark900,
            )
            Text(
                text = event.venue,
                style = MaterialTheme.typography.inter14Normal,
                color = MaterialTheme.colorScheme.dark700,
            )
        }
    }
    }
}

@Composable
private fun InfoRow(event: EventUi) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.white900, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 20.dp),
    ) {
        InfoBarItem(
            iconRes = uiRes.drawable.ic_dollar_filled,
            title = stringResource(Res.string.participation),
            info = stringResource(uiRes.string.rubles_template, event.participantPrice)
        )
        VerticalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.dark700)
        InfoBarItem(
            iconRes = uiRes.drawable.ic_calendar_filled,
            title = stringResource(Res.string.date),
            info = "${event.startsAt?.day} ${event.startsAt?.month?.name}",
        )
        VerticalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.dark700)
        InfoBarItem(
            iconRes = uiRes.drawable.ic_pin_filled,
            title = stringResource(Res.string.location),
            info = event.city
        )
    }
}

@Composable
private fun OrganizerRow(name: String, viewCount: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            painter = painterResource(uiRes.drawable.image_profile_placeholder),
            contentDescription = null,
            modifier = Modifier.size(52.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.inter14Normal,
                color = MaterialTheme.colorScheme.dark900,
            )
            Text(
                text = stringResource(Res.string.organizer),
                style = MaterialTheme.typography.inter12Normal,
                color = MaterialTheme.colorScheme.dark700,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = viewCount.toString(),
                    style = MaterialTheme.typography.inter12Normal,
                    color = MaterialTheme.colorScheme.dark700,
                )
                Icon(
                    painter = painterResource(uiRes.drawable.ic_eye),
                    tint = MaterialTheme.colorScheme.dark700,
                    contentDescription = viewCount.toString(),
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}

@Composable
private fun InfoBarItem(
    iconRes: DrawableResource,
    title: String,
    info: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary900,
                modifier = Modifier.size(12.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.inter12Normal,
                color = MaterialTheme.colorScheme.dark700,
            )
        }
        Text(
            text = info,
            style = MaterialTheme.typography.inter14Bold,
            color = MaterialTheme.colorScheme.primary900,
            textAlign = TextAlign.Center,
        )
    }
}

@MobilePreview
@Composable
private fun EventBodyPreview() {
    EventBody(State())
}

@MobilePreview
@Composable
private fun EventScreenPreview() {
    EventInfoContent(State())
}