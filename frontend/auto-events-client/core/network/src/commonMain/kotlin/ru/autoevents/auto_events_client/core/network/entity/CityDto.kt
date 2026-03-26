package ru.autoevents.auto_events_client.core.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
)