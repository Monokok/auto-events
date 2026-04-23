package ru.autoevents.auto_events_client.core.network.di

import io.ktor.client.*
import org.koin.dsl.module
import ru.autoevents.auto_events_client.core.network.api.AuthorizationApi
import ru.autoevents.auto_events_client.core.network.api.EventApi
import ru.autoevents.auto_events_client.core.network.api.UsersApi
import ru.autoevents.auto_events_client.core.network.api.VenueApi
import ru.autoevents.auto_events_client.core.network.client.createHttpClient

val networkModule
    get() = module {
        single<HttpClient> { createHttpClient() }
        factory { EventApi(get()) }
        factory { VenueApi(get()) }
        factory { AuthorizationApi(get()) }
        factory { UsersApi(get()) }
    }