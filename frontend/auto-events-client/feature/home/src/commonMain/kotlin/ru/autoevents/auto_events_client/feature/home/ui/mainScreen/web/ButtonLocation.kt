package ru.autoevents.auto_events_client.feature.home.ui.mainScreen.web

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import auto_events_client.core.ui.generated.resources.Res
import auto_events_client.core.ui.generated.resources.current_location
import auto_events_client.core.ui.generated.resources.ic_chevron_down
import auto_events_client.core.ui.generated.resources.ic_close
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.autoevents.auto_events_client.feature.home.data.model.CityUi

@Composable
fun ButtonLocation(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = false,
    defaultSelectedCityName: String? = null,
    setLocation: () -> Unit = {},
    resetLocation: () -> Unit = {},
) {

    var showCityDropdownMenu by remember { mutableStateOf(isExpanded) }

    var selectedCity by remember {
        mutableStateOf<CityUi?>(
            if (defaultSelectedCityName != null) CityUi("Наименование города", 0)
            else null
        )
    }

    //Обработчик нажатия на кнопку сброса города
    fun handleResetLocation() {
        resetLocation()     //вызов прокинутой функции - для установки состояния где-то свыше
        selectedCity = null //прекращаем рисовать имя города
    }

    val cityList = listOf(
        CityUi("Москва", 0),
        CityUi("Иваново", 1),
        CityUi("Санкт-Петербург", 2),
        CityUi("Петропавловск Камчатский", 3),
        CityUi("Нижний Новгород", 4),
        CityUi("Владимир", 5),
        CityUi("Город с очень длинным названием", 6),
        CityUi("Шуя", 7),
    )

    Box(
        modifier = modifier.width(210.dp).clip(RoundedCornerShape(50.dp))
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
//            horizontalAlignment = Alignment.CenterHorizontally,
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
                IconButton(
                    onClick = { showCityDropdownMenu = true }, modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_chevron_down),
                        contentDescription = "Выбрать локацию",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Правая иконка (крестик)
                if (selectedCity != null)
                    IconButton(
                    onClick = {
                        showCityDropdownMenu = false
                        handleResetLocation()
                    },
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_close),
                        contentDescription = "Закрыть",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Box {
                    DropdownMenu(
                        expanded = showCityDropdownMenu, onDismissRequest = { showCityDropdownMenu = false }) {
                        cityList.forEach { cityElement ->
                            DropdownMenuItem(text = {
                                Text(
                                    cityElement.name,
                                    style = MaterialTheme.typography.labelSmall,
//                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }, onClick = {
                                // Здесь можно добавить обработку выбора города
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
    }
}

@Preview(widthDp = 300, heightDp = 300)
@Composable
private fun ButtonLocationPreviewExpanded() {
    Row {
        ButtonLocation(isExpanded = true)
    }
}

@Preview(widthDp = 300, heightDp = 300)
@Composable
private fun ButtonLocationPreview() {
    Row {
        ButtonLocation(isExpanded = false)
    }
}

@Preview(widthDp = 300, heightDp = 300)
@Composable
private fun ButtonLocationCityIsSelected() {
    Row {
        ButtonLocation(isExpanded = false, defaultSelectedCityName = "Наименование города")
    }
}

