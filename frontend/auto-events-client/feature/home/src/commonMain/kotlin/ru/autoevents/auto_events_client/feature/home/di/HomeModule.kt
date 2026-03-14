package ru.autoevents.auto_events_client.feature.home.di

import org.koin.dsl.module
import ru.autoevents.auto_events_client.feature.home.data.useCases.GetEventListUseCase
import ru.autoevents.auto_events_client.feature.home.domain.EventRepository
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.MainScreenModel

val homeScreensModule
    get() = module {
        single { EventRepository(get()) }
        single { GetEventListUseCase(get()) }
        factory { MainScreenModel(get()) }
    }