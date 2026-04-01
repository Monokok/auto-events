package ru.autoevents.auto_events_client.feature.home.ui.main.web

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.Res
import auto_events_client.core.ui.generated.resources.current_location
import auto_events_client.core.ui.generated.resources.ic_close
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
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
fun ButtonLocation(
    cities: List<CityUi>,
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    defaultSelectedCity: CityUi? = null,
    setLocation: (CityUi) -> Unit = {},
    resetLocation: () -> Unit = {},
) {

    var showCityDropdownMenu by remember { mutableStateOf(isExpanded) }

    var selectedCity by remember {
        mutableStateOf<CityUi?>(
            if (defaultSelectedCity != null) CityUi("Наименование города", 0)
            else null
        )
    }

    //Обработчик нажатия на кнопку сброса города
    fun handleResetLocation() {
        resetLocation()     //вызов прокинутой функции - для установки состояния где-то свыше
        selectedCity = null //прекращаем рисовать имя города
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.height(IntrinsicSize.Min),
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .width(210.dp)
                .clip(RoundedCornerShape(50.dp))
                .clickable { showCityDropdownMenu = !isExpanded }
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                .padding(horizontal = 25.dp, vertical = 16.dp)
        ) {
            // Строка "Текущая локация"
            Row(
                modifier = Modifier.fillMaxWidth(),
//                    .padding(start = 20.dp)
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(Res.string.current_location),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
//                IconButton(
//                    onClick = { showCityDropdownMenu = true }, modifier = Modifier.size(24.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(Res.drawable.ic_chevron_down),
//                        contentDescription = "Выбрать локацию",
//                        tint = MaterialTheme.colorScheme.onPrimary,
//                        modifier = Modifier.size(20.dp)
//                    )
//                }

                // Правая иконка (крестик)
                Box {
                    DropdownMenu(
                        expanded = showCityDropdownMenu, onDismissRequest = { showCityDropdownMenu = false }) {
                        cities.forEach { cityElement ->
                            DropdownMenuItem(text = {
                                Text(
                                    cityElement.name,
                                    style = MaterialTheme.typography.labelSmall,
//                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }, onClick = {
                                // Здесь можно добавить обработку выбора города
                                setLocation(cityElement)
                                showCityDropdownMenu = false
                                selectedCity = cityElement
                            })
                        }
                    }


                }
            }
            selectedCity?.let {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
        AnimatedVisibility(selectedCity != null, modifier = Modifier.fillMaxHeight(),) {
            IconButton(
                onClick = {
                    showCityDropdownMenu = false
                    handleResetLocation()
                },
                modifier = Modifier.fillMaxHeight().aspectRatio(1f),
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_close),
                    contentDescription = "Закрыть",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(10.dp).fillMaxHeight().aspectRatio(1f),
                )
            }
        }
    }
}

@Preview(widthDp = 300, heightDp = 300)
@Composable
private fun ButtonLocationPreviewExpanded() {
    Row {
        ButtonLocation(
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
        ButtonLocation(
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
        ButtonLocation(
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

