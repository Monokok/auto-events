package ru.autoevents.auto_events_client.core.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityResponseDto(
    @SerialName("cities") val cities: List<CityDto>? = null,
)

@Serializable
data class CityDto(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
)