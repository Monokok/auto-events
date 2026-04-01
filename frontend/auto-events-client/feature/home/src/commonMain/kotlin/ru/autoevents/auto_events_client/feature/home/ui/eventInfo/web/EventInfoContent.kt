package ru.autoevents.auto_events_client.feature.home.ui.eventInfo.web

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import ru.autoevents.auto_events_client.core.ui.components.Header
import ru.autoevents.auto_events_client.core.ui.components.LoaderFullScreen
import ru.autoevents.auto_events_client.core.ui.components.Screen
import ru.autoevents.auto_events_client.core.ui.components.WebPreview
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi
import ru.autoevents.auto_events_client.feature.home.ui.eventInfo.State


@Composable
internal fun EventInfoContent(state: State) {
    val navigator = LocalNavigator.current
    Screen(
        topBar = {
            Header(
                navigateBack = { navigator?.pop() }
            )
        }
    ) {
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
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Картинка с авто
        Box(
            modifier = Modifier.size(320.dp).clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {

            // Image(painter = painterResource(id = R.drawable.car), contentDescription = null)

            // Заглушка для картинки
//            Icon(
//                imageVector = Icons.Default.DirectionsCar,
//                contentDescription = null,
//                modifier = Modifier
//                    .size(48.dp)
//                    .align(Alignment.Center),
//                tint = MaterialTheme.colorScheme.primary
//            )
        }

        // Столбец с информацией
        Column(
//            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Организатор
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
//                Icon(
//                    imageVector = Icons.Default.AccountCircle,
//                    contentDescription = null,
//                    modifier = Modifier.size(20.dp),
//                    tint = MaterialTheme.colorScheme.primary
//                )

                Column {
                    Text(
                        text = "ФАС Иваново", // В реальном проекте брать из event.organizerName
                        style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Организатор",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Заголовок мероприятия
            Text(
                text = event.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold
            )

            // Раздел "О событии"
            Text(
                text = "О событии", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold
            )

            // Описание события
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )

            // Горизонтальный ряд с информационными блоками
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(150.dp, alignment = Alignment.CenterHorizontally)
            ) {
                // Участие
                InfoColumn(
//                    icon = Icons.Default.AttachMoney,
                    label = "Участие",
                    value = if (event.isFree) "Бесплатно" else "Платно",
                    tint = if (!event.isFree) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Дата
                event.startsAt?.let {date ->
                    InfoColumn(
//                    icon = Icons.Default.Event,
                        label = "Дата",
                        value = "${date.day} ${date.month.name} ${date.year}",
                    )
                }

                // Локация
                InfoColumn(
//                    icon = Icons.Default.LocationOn,
                    label = "Локация", value = event.city
                )
            }
        }
    }
}

@Composable
private fun InfoColumn(
    icon: ImageVector? = null, label: String, value: String, tint: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = null,
//                modifier = Modifier.size(16.dp),
//                tint = tint
//            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Text(
            text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium
        )
    }
}

@WebPreview
@Composable
private fun EventBodyPreview() {
    EventBody(State())
}

@WebPreview
@Composable
private fun EventScreenPreview() {
    EventInfoContent(State())
}