package ru.autoevents.auto_events_client.feature.home.di

import org.koin.dsl.module
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.MainScreenModel

val homeScreensModule
    get() = module {
        factory { MainScreenModel() }
    }