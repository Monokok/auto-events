package ru.autoevents.auto_events_client.feature.profile.data

import ru.autoevents.auto_events_client.core.network.entity.UserResponseDto
import ru.autoevents.auto_events_client.feature.profile.domain.model.ProfileDataUi

fun UserResponseDto.mapToUi(): ProfileDataUi = ProfileDataUi(
    id = id,
    username = username,
    email = email,
    role = role,
)