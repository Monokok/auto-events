package ru.autoevents.auto_events_client.feature.home.di

import io.ktor.client.*
import org.koin.dsl.module
import ru.autoevents.auto_events_client.feature.home.data.useCases.GetEventListUseCase
import ru.autoevents.auto_events_client.feature.home.domain.EventRepository
import ru.autoevents.auto_events_client.feature.home.domain.api.EventApi
import ru.autoevents.auto_events_client.feature.home.domain.api.createHttpClient
import ru.autoevents.auto_events_client.feature.home.ui.mainScreen.MainScreenModel

val homeScreensModule
    get() = module {
        single<HttpClient> { //ПЕРЕНЕСТИ В КОР
            val engine = null //временно
            createHttpClient(engine)
        }
        factory { EventApi(get(), baseUrl = "http://144.31.27.9:8010") }
        single { EventRepository(get()) }
        single { GetEventListUseCase(get()) }
        factory { MainScreenModel(get()) }
    }