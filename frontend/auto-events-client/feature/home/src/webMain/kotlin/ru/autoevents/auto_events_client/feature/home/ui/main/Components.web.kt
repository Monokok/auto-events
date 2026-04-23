package ru.autoevents.auto_events_client.feature.home.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.autoevents.auto_events_client.feature.home.domain.model.CityUi
import ru.autoevents.auto_events_client.feature.home.ui.main.web.WebButtonLocation

@Composable
actual fun ButtonLocation(
    cities: List<CityUi>,
    modifier: Modifier,
    isExpanded: Boolean,
    defaultSelectedCity: CityUi?,
    setLocation: (CityUi) -> Unit,
    resetLocation: () -> Unit
) {
    WebButtonLocation(cities, modifier, isExpanded, defaultSelectedCity, setLocation, resetLocation)
}