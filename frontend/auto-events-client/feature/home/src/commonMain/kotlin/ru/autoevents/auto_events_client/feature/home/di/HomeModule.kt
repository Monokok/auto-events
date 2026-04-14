package ru.autoevents.auto_events_client.feature.home.di

import org.koin.dsl.module
import ru.autoevents.auto_events_client.feature.home.data.AuthorizationRepository
import ru.autoevents.auto_events_client.feature.home.data.EventRepository
import ru.autoevents.auto_events_client.feature.home.data.UsersRepository
import ru.autoevents.auto_events_client.feature.home.domain.useCases.*
import ru.autoevents.auto_events_client.feature.home.ui.eventInfo.EventInfoScreenModel
import ru.autoevents.auto_events_client.feature.home.ui.main.MainScreenModel

val homeScreensModule
    get() = module {
        single { EventRepository(get(), get()) }
        single { GetEventListUseCase(get()) }
        single { GetEventTypesListUseCase(get()) }
        single { GetCitiesUseCase(get()) }
        single { GetEventInfoUseCase(get()) }


        single { AuthorizationRepository(get()) }
        single { UsersRepository(get()) }
        single { AuthorizationUseCase(get(), get()) }

        factory { MainScreenModel(get(), get(), get()) }
        factory { EventInfoScreenModel(get()) }
    }