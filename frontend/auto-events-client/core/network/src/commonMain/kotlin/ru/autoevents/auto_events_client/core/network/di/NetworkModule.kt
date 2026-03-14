package ru.autoevents.auto_events_client.core.network.di

import io.ktor.client.*
import org.koin.dsl.module
import ru.autoevents.auto_events_client.core.network.api.EventApi
import ru.autoevents.auto_events_client.core.network.client.createHttpClient

val networkModule
    get() = module {
        single<HttpClient> {
            val engine = null
            createHttpClient(engine)
        }
        factory { EventApi(get()) }
    }