package ru.autoevents.auto_events_client.feature.register.data

import ru.autoevents.auto_events_client.core.network.entity.LoginResponseDto
import ru.autoevents.auto_events_client.core.network.entity.RegisterResponseDto
import ru.autoevents.auto_events_client.core.network.entity.UserResponseDto
import ru.autoevents.auto_events_client.feature.register.domain.model.RegisterDataUi

fun RegisterResponseDto.mapToUi(): RegisterDataUi = RegisterDataUi(
    id = id ?: 0,
    username = username.orEmpty(),
    email = email.orEmpty(),
    role = role.orEmpty(),
)