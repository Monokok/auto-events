package ru.autoevents.auto_events_client.feature.profile.di

import org.koin.dsl.module
import ru.autoevents.auto_events_client.feature.profile.ui.profile.ProfileScreenModel

val profileScreensModule
    get() = module {
        single { ProfileScreenModel() }
    }