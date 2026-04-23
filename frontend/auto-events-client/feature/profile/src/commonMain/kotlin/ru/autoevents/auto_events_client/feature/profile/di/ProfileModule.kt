package ru.autoevents.auto_events_client.feature.profile.di

import org.koin.dsl.module
import ru.autoevents.auto_events_client.core.common.auth.AccessTokenProvider
import ru.autoevents.auto_events_client.core.common.auth.EmptyAccessTokenProvider
import ru.autoevents.auto_events_client.feature.profile.data.UsersRepository
import ru.autoevents.auto_events_client.feature.profile.domain.useCases.GetUserProfileUseCase
import ru.autoevents.auto_events_client.feature.profile.ui.profile.ProfileScreenModel

val profileScreensModule
    get() = module {
        single<AccessTokenProvider> { EmptyAccessTokenProvider() }
        single { UsersRepository(get()) }
        single { GetUserProfileUseCase(get()) }
        factory { ProfileScreenModel(get(), get()) }
    }
