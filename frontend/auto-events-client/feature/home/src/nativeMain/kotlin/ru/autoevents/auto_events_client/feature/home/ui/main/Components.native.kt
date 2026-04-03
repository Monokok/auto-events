package ru.autoevents.auto_events_client.feature.home.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.autoevents.auto_events_client.feature.home.domain.model.CityUi
import ru.autoevents.auto_events_client.feature.home.ui.main.mobile.MobileButtonLocation

@Composable
actual fun ButtonLocation(
    cities: List<CityUi>,
    modifier: Modifier,
    isExpanded: Boolean,
    defaultSelectedCity: CityUi?,
    setLocation: (CityUi) -> Unit,
    resetLocation: () -> Unit
) {
    MobileButtonLocation(cities, modifier, isExpanded, defaultSelectedCity, setLocation, resetLocation)
}