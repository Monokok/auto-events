package ru.autoevents.auto_events_client.core.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("role")
    val role: String? = null,
)
