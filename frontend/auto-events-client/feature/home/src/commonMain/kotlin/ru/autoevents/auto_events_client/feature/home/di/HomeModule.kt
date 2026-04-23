package ru.autoevents.auto_events_client.feature.home.di

import org.koin.dsl.module
import ru.autoevents.auto_events_client.feature.home.data.EventRepository
import ru.autoevents.auto_events_client.feature.home.domain.useCases.GetCitiesUseCase
import ru.autoevents.auto_events_client.feature.home.domain.useCases.GetEventInfoUseCase
import ru.autoevents.auto_events_client.feature.home.domain.useCases.GetEventListUseCase
import ru.autoevents.auto_events_client.feature.home.domain.useCases.GetEventTypesListUseCase
import ru.autoevents.auto_events_client.feature.home.ui.eventInfo.EventInfoScreenModel
import ru.autoevents.auto_events_client.feature.home.ui.main.MainScreenModel

val homeScreensModule
    get() = module {
        single { EventRepository(get(), get()) }
        single { GetEventListUseCase(get()) }
        single { GetEventTypesListUseCase(get()) }
        single { GetCitiesUseCase(get()) }
        single { GetEventInfoUseCase(get()) }

        factory { MainScreenModel(get(), get(), get()) }
        factory { EventInfoScreenModel(get()) }
    }