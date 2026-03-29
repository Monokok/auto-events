package ru.autoevents.auto_events_client.feature.home.data.model

data class EventTypeUi(
    val name: String,
    val id: Int,
    val isSelected: Boolean = false //флаг применен или не применен фильтр,
)