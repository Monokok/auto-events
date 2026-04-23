package ru.autoevents.auto_events_client.feature.home.domain.model

data class EventTypeUi(
    val name: String,
    val id: Int,
    val isSelected: Boolean = false //флаг применен или не применен фильтр,
)