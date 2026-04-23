package ru.autoevents.auto_events_client.feature.login.data

import ru.autoevents.auto_events_client.core.network.entity.LoginResponseDto
import ru.autoevents.auto_events_client.core.network.entity.RegisterResponseDto
import ru.autoevents.auto_events_client.core.network.entity.UserResponseDto
import ru.autoevents.auto_events_client.feature.login.domain.model.ProfileDataUi

fun UserResponseDto.mapToUi(): ProfileDataUi = ProfileDataUi(
    id = id,
    username = username,
    email = email,
    role = role,
)
fun LoginResponseDto.mapToUi(): String = (accessToken)
