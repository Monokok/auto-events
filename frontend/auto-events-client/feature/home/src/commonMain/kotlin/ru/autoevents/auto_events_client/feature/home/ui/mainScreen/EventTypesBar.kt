package ru.autoevents.auto_events_client.feature.home.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import ru.autoevents.auto_events_client.core.ui.theme.white900
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.Res
import auto_events_client.core.ui.generated.resources.ic_all
import auto_events_client.feature.home.generated.resources.all
import auto_events_client.feature.home.generated.resources.event_type_content_description
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.components.MobilePreview
import ru.autoevents.auto_events_client.core.ui.components.WebPreview
import ru.autoevents.auto_events_client.core.ui.theme.cardRadius10
import ru.autoevents.auto_events_client.core.ui.theme.cardRadius16
import ru.autoevents.auto_events_client.core.ui.theme.dark700
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal
import ru.autoevents.auto_events_client.core.ui.theme.primary900
import auto_events_client.feature.home.generated.resources.Res as ResFeature

/**
 * Бар с кнопками для выбора типов событий для фильтра
 * @param [eventTypes]
 *
 */
@Composable
fun EventTypesBar(
    eventTypes: List<EventTypeUi>,
    onEventTypeClick: (EventTypeUi) -> Unit,
    ){
    LazyRow {
        items(eventTypes) {
            eventType ->
            EventTypeButton(
                eventType
            )
        }
    }


}

@Composable
private fun EventTypeButton(
    type: EventTypeUi,
    icon: Painter? = null,
    primaryColor: Color? = null,
    onPrimaryColor: Color? = null,
){
    val isSelected by remember{mutableStateOf(false)}
    val backgroundColor = primaryColor ?: MaterialTheme.colorScheme.primary
    val contentColor = onPrimaryColor ?: MaterialTheme.colorScheme.onPrimary

    Row(
        modifier = Modifier.width(80.dp).height(40.dp)
            .clip(MaterialTheme.shapes.cardRadius10)
            .background(color = backgroundColor, shape = MaterialTheme.shapes.cardRadius16),
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let {
            Icon(
                painter = icon,
                tint = contentColor,
                contentDescription = stringResource(
                    ResFeature.string.event_type_content_description,
                    type.name
                ),
                modifier = Modifier
            )
        }

        Text(
            modifier = Modifier.background(color = backgroundColor),
            style = MaterialTheme.typography.inter14Normal,
            color = contentColor,
            text = type.name
        )

    }
}

private fun getResetEventTypeButton(): @Composable () -> Unit {
    return {
        EventTypeButton(
            EventTypeUi(
                name = stringResource(ResFeature.string.all),
                id = -1
            ),
            painterResource(Res.drawable.ic_all),
            MaterialTheme.colorScheme.primary900,
            MaterialTheme.colorScheme.white900
        )
    }
}

@Preview
@Composable
private fun EventTypeButton_Preview(){
    getResetEventTypeButton().invoke()
}

@Composable
@Preview(widthDp = 1000)
fun EventTypesBar_Preview(){
    EventTypesBar(
        eventTypes = getEventTypesMock(),
        onEventTypeClick = {},

    )
}

data class EventTypeUi(
    val name: String,
    val id: Int
)

fun getEventTypesMock(): List<EventTypeUi>{
    return listOf(
        EventTypeUi(
            "Автовстреча",
            10
        ),
        EventTypeUi(
            "Автокросс",
            5
        ),
        EventTypeUi(
            "Автослалом",
            18
        ),
        EventTypeUi(
            "Автошоу",
            11
        ),
        EventTypeUi(
            "Выставка",
            15
        ),
        EventTypeUi(
            "Гонки",
            16
        ),
        EventTypeUi(
            "Джимхана",
            9
        ),
        EventTypeUi(
            "Дрифт",
            2
        ),
        EventTypeUi(
            "Дрэг-рейсинг",
            3
        ),
        EventTypeUi(
            "Картинг",
            17
        ),
        EventTypeUi(
            "Кольцевые гонки",
            1
        ),
        EventTypeUi(
            "Ралли",
            6
        ),
        EventTypeUi(
            "Ралликросс",
            7
        ),
        EventTypeUi(
            "Тайм-аттак",
            4
        ),
        EventTypeUi(
            "Трек-день",
            8
        ),
        EventTypeUi(
            "Триал 4x4",
            12
        ),
        EventTypeUi(
            "Фестиваль автозвука",
            14
        ),
        EventTypeUi(
            "Фестиваль автоспорта",
            13
        )
    )
}