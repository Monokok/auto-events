package ru.autoevents.auto_events_client.feature.home.ui.mainScreen.web

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.feature.home.generated.resources.Res
import auto_events_client.feature.home.generated.resources.ic_arrow_down
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import ru.autoevents.auto_events_client.core.ui.components.Screen
import ru.autoevents.auto_events_client.core.ui.components.WebPreview
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal
import ru.autoevents.auto_events_client.core.ui.theme.inter48ExtraBold
import ru.autoevents.auto_events_client.feature.home.data.model.EventUi
import kotlin.time.Instant


@Composable
fun EventScreen() {
    Screen {
        Column {
            Header()
            EventBody(Modifier.padding(horizontal = 20.dp, vertical = 20.dp))
            Footer()
        }
    }
}


@Composable
fun EventBody(modifier: Modifier = Modifier) {
    val mockedEvent = EventUi(
        id = 1,
        title = "Drift Showdown",
        description = "Грандиозное дрифт-шоу с участием лучших пилотов России и ближнего зарубежья. Зрелищные заезды, дым из-под колес и невероятная атмосфера ждут вас на автодроме Moscow Raceway. В программе: квалификация, парные заезды, показательные выступления и автограф-сессия с пилотами.",
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

    EventBodyContent(event = mockedEvent, modifier = modifier)
}

@Composable
fun EventBodyContent(
    event: EventUi,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Картинка с авто
        Box(
            modifier = Modifier
                .size(320.dp)
                .clip(RoundedCornerShape(12.dp))
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
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
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
                text = event.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            // Раздел "О событии"
            Text(
                text = "О событии",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
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
                InfoColumn(
//                    icon = Icons.Default.Event,
                    label = "Дата",
                    value = event.startsAt.toString()
                )

                // Локация
                InfoColumn(
//                    icon = Icons.Default.LocationOn,
                    label = "Локация",
                    value = event.city
                )
            }
        }
    }
}

@Composable
fun InfoColumn(
    icon: ImageVector? = null,
    label: String,
    value: String,
    tint: Color = MaterialTheme.colorScheme.onSurfaceVariant
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
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
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@WebPreview
@Composable
fun EventBodyPreview(){
    EventBody()
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
    ) {
        FooterItemButton(
            "Главная",
            null, {}
        )
        FooterItemButton(
            "Организаторам",
            null, {}
        )
        FooterItemButton(
            "Модераторам",
            null, {}
        )
        FooterItemButton(
            "О нас",
            null, {}
        )
    }
}

@Composable
fun FooterItemButton(
    text: String = "Button",
    icon: ImageVector? = null,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary // цвет текста
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary), // рамка
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = text)
        }

    }
}


@Preview
@Composable
fun FooterItemButtonPreview() {
    FooterItemButton("Кнопка", onClick = {})
}

@Composable
@Preview(widthDp = 1200)
fun FooterPreview() {
    Footer()
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 20.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.padding(start = 60.dp, top = 30.dp, bottom = 30.dp, end = 20.dp)) {
            Text(
                text = "Автомобильные события",
                style = MaterialTheme.typography.inter48ExtraBold,//MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Ярче, громче, быстрее, ближе, захватывающе!",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.inter14Normal
            )
        }
        ButtonLocation(modifier = Modifier.padding(end = 30.dp), "г. Иваново")
    }
}

@Preview(widthDp = 1200)
@Composable
fun HeaderPreview() {
    Header()
}

@Composable
fun ButtonLocation(
    modifier: Modifier = Modifier,
    location: String,
    onLocationClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .width(200.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Текущая локация",
                style = MaterialTheme.typography.bodyMedium, // inter14Normal замени на существующий
                color = MaterialTheme.colorScheme.onPrimary,
            )

            IconButton(
                onClick = onLocationClick,
                modifier = Modifier.size(24.dp) // IconButton сам имеет встроенные отступы и ripple эффект
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_arrow_down),
                    contentDescription = "Выбрать локацию",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(20.dp) // Размер самой иконки
                )
            }
        }

        Text(
            text = location,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(start = 20.dp, bottom = 10.dp)
        )
    }
}

@Preview
@Composable
fun ButtonLocationPreview() {
    ButtonLocation(location = "г. Иваново")
}

@WebPreview
@Composable
fun EventScreenPreview() {
    EventScreen()
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