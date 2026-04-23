package ru.autoevents.auto_events_client.core.ui.components


/**
 * Элемент навигации.
 *
 * @property title Текст, отображаемый на кнопке
 * @property onClick Действие, выполняемое при нажатии на кнопку
 */
data class NavItem(
    val title: String,
    val onClick: () -> Unit
)