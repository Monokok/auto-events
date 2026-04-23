package ru.autoevents.auto_events_client.core.common.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.autoevents.auto_events_client.core.common.tokenStorage.PlatformTokenStorage
import ru.autoevents.auto_events_client.core.common.tokenStorage.TokenStorage

val androidModule = module {

    single<TokenStorage> {
        PlatformTokenStorage(androidContext())
    }
}