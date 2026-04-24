
package ru.autoevents.auto_events_client.feature.home.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
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
import ru.autoevents.auto_events_client.core.ui.theme.*
import ru.autoevents.auto_events_client.feature.home.domain.model.EventTypeUi
import auto_events_client.feature.home.generated.resources.Res as ResFeature

/**
 * Бар с кнопками для выбора типов событий для фильтра
 * Бар сам должен понимать - как рисовать кнопку "Все"
 * @param [eventTypes]
 *
 */
@Composable
fun EventTypesBar(
    eventTypes: List<EventTypeUi>,
    onEventTypeClick: (EventTypeUi) -> Unit,
    clearEventTypes: () -> Unit,
) {
    val isResetFilterButtonActive =
        eventTypes.none { it.isSelected } //если хотя бы один фильтр применен - false (кнопка "Все" не применена), иначе - true
    LazyRow(
        modifier = Modifier,
        contentPadding = PaddingValues(horizontal = 12.dp) //отступы по краям
        , horizontalArrangement = Arrangement.spacedBy(8.dp) //отступы между кнопочками
    ) {
        item {
            ResetEventTypeButton(
                clearEventTypes,
                isResetFilterButtonActive
            )
        }
        items(eventTypes) { eventType ->
            EventTypeButton(
                eventType,
                onEventTypeClick = {
                    //it.isSelected = !it.isSelected
                    onEventTypeClick(it) //isSelected менять сверху
                },
            )
        }
    }
}

@Composable
private fun EventTypeButton(
    type: EventTypeUi,
    onEventTypeClick: (value: EventTypeUi) -> Unit, //callback для применения установленного значения свыше - пробрасываем evenTypeUi, из которого возьмем id, name - мб тоже где-то пригодиться
    icon: Painter? = null,
    primaryColor: Color? = null,
    onPrimaryColor: Color? = null,
) {
    val isSelected = type.isSelected
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primary //выделенность
        } else {
            MaterialTheme.colorScheme.secondaryContainer //не выделенность
        },
        label = "bgColor"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSecondaryContainer
        },
        label = "contentColor"
    )

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 1f,
        label = "scale"
    )

    val elevation by animateDpAsState(
        targetValue = if (isSelected) 6.dp else 0.dp,
        label = "elevation"
    )

    Row(
        modifier = Modifier.scale(scale)
            .shadow(elevation, shape = MaterialTheme.shapes.cardRadius16)
            .wrapContentWidth().height(40.dp)
            .clip(MaterialTheme.shapes.cardRadius10)
            .clickable {
                //Вызов callback. Логика изменения вне компонента
                onEventTypeClick(type)
            }
            .background(color = backgroundColor, shape = MaterialTheme.shapes.cardRadius16)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
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

@Composable
private fun ResetEventTypeButton(
    clearEventTypes: () -> Unit //callback игнорирования всех ранее выбранных фильтров и установка фильтрации "Все"
    ,
    isSelected: Boolean = false
) {
    EventTypeButton(
        EventTypeUi(
            name = stringResource(ResFeature.string.all), id = -1, isSelected = isSelected
        ),
        { clearEventTypes() },
        painterResource(Res.drawable.ic_all),
        MaterialTheme.colorScheme.primary900,
        MaterialTheme.colorScheme.white900
    )
}

@Preview
@Composable
private fun EventTypeButton_Preview() {
    ResetEventTypeButton({ println("Фильтры сброшены") })
}

@Composable
@Preview(widthDp = 1000)
fun EventTypesBar_Preview() {
    var eventTypes by remember { mutableStateOf(getEventTypesMock()) }


    EventTypesBar(
        eventTypes = eventTypes,
        onEventTypeClick = { clickedType ->
            eventTypes = eventTypes.map { type ->
                if (type.id == clickedType.id) {
                    type.copy(isSelected = !type.isSelected)
                } else {
                    type
                }
            }
        },
        clearEventTypes = {

            eventTypes = eventTypes.map { type ->
                if (type.isSelected) {
                    type.copy(isSelected = false)
                } else {
                    type
                }
            }
        },
    )
}


fun getEventTypesMock(): List<EventTypeUi> {
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