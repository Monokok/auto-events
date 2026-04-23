package ru.autoevents.auto_events_client.feature.home.ui.main.mobile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.Res
import auto_events_client.core.ui.generated.resources.all
import auto_events_client.core.ui.generated.resources.current_location
import auto_events_client.core.ui.generated.resources.ic_chevron_down
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.core.ui.theme.dark700
import ru.autoevents.auto_events_client.core.ui.theme.dark900
import ru.autoevents.auto_events_client.core.ui.theme.inter14Bold
import ru.autoevents.auto_events_client.core.ui.theme.inter14Normal
import ru.autoevents.auto_events_client.feature.home.domain.model.CityUi

/**
 * Компонент кнопки, которая задает локацию через выпадающий список из городов
 * @param cities список городов, которые доступны для выбора
 * @param isExpanded параметр, определяющий раскрыт ли список (по умолчанию - false)
 * @param defaultSelectedCity город, устанавливаемый по умолчанию в UI кнопки
 * @param setLocation колбэк для установки выбранного города
 * @param resetLocation колбэк для сброса выбранного города
 */
@Composable
fun MobileButtonLocation(
    cities: List<CityUi>,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    defaultSelectedCity: CityUi? = null,
    setLocation: (CityUi) -> Unit = {},
    resetLocation: () -> Unit = {},
) {

    var showCityDropdownMenu by remember { mutableStateOf(isExpanded) }

    var selectedCity by remember {
        mutableStateOf(
            if (defaultSelectedCity != null) CityUi("Наименование города", 0)
            else null
        )
    }

    //Обработчик нажатия на кнопку сброса города
    fun handleResetLocation() {
        resetLocation()     //вызов прокинутой функции - для установки состояния где-то свыше
        selectedCity = null //прекращаем рисовать имя города
    }

    Box {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clip(CircleShape)
                .clickable { showCityDropdownMenu = !isExpanded }
                .padding(horizontal = 12.dp, vertical = 8.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
            ) {
                AnimatedVisibility(selectedCity == null) {
                    Text(
                        text = stringResource(Res.string.current_location),
                        style = MaterialTheme.typography.inter14Normal,
                        color = MaterialTheme.colorScheme.dark700,
                    )
                }
                selectedCity?.let {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.inter14Bold,
                        color = MaterialTheme.colorScheme.dark900,
                    )
                }
            }
            Icon(
                painter = painterResource(Res.drawable.ic_chevron_down),
                contentDescription = "Открыть",
                tint = MaterialTheme.colorScheme.dark700,
                modifier = Modifier.size(24.dp),
            )
//            AnimatedVisibility(visible = selectedCity != null) {
//                Icon(
//                    painter = painterResource(Res.drawable.ic_close_outlined),
//                    contentDescription = "Сбросить",
//                    tint = MaterialTheme.colorScheme.dark700,
//                    modifier = Modifier
//                        .clip(CircleShape)
//                        .clickable {
//                            showCityDropdownMenu = false
//                            handleResetLocation()
//                        }
//                        .size(24.dp),
//                )
//            }
        }
        DropdownMenu(
            expanded = showCityDropdownMenu,
            onDismissRequest = { showCityDropdownMenu = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        stringResource(Res.string.all),
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                onClick = {
                    showCityDropdownMenu = false
                    handleResetLocation()

                }
            )
            cities.forEach { cityElement ->
                DropdownMenuItem(
                    text = {
                        Text(
                            cityElement.name,
                            style = MaterialTheme.typography.labelSmall,
//                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    },
                    onClick = {
                        // Здесь можно добавить обработку выбора города
                        setLocation(cityElement)
                        showCityDropdownMenu = false
                        selectedCity = cityElement
                    }
                )
            }
        }
    }
}

@Preview(widthDp = 300, heightDp = 300)
@Composable
private fun ButtonLocationPreviewExpanded() {
    Row {
        MobileButtonLocation(
            isExpanded = true, cities = listOf(
                CityUi("Москва", 0),
                CityUi("Иваново", 1),
                CityUi("Санкт-Петербург", 2),
                CityUi("Петропавловск Камчатский", 3),
                CityUi("Нижний Новгород", 4),
                CityUi("Владимир", 5),
                CityUi("Город с очень длинным названием", 6),
                CityUi("Шуя", 7),
            )
        )
    }
}

@Preview(widthDp = 300, heightDp = 300)
@Composable
private fun ButtonLocationPreview() {
    Row {
        MobileButtonLocation(
            isExpanded = false,
            cities = listOf(
                CityUi("Москва", 0),
                CityUi("Иваново", 1),
                CityUi("Санкт-Петербург", 2),
                CityUi("Петропавловск Камчатский", 3),
                CityUi("Нижний Новгород", 4),
                CityUi("Владимир", 5),
                CityUi("Город с очень длинным названием", 6),
                CityUi("Шуя", 7),
            )
        )
    }
}

@Preview(widthDp = 300, heightDp = 300)
@Composable
private fun ButtonLocationCityIsSelected() {
    Row {
        MobileButtonLocation(
            isExpanded = false, defaultSelectedCity = CityUi("Наименование города", 0),
            cities = listOf(
                CityUi("Москва", 0),
                CityUi("Иваново", 1),
                CityUi("Санкт-Петербург", 2),
                CityUi("Петропавловск Камчатский", 3),
                CityUi("Нижний Новгород", 4),
                CityUi("Владимир", 5),
                CityUi("Город с очень длинным названием", 6),
                CityUi("Шуя", 7),
            )
        )
    }
}

