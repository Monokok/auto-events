package ru.autoevents.auto_events_client.core.ui.di

import org.koin.dsl.module

val uiModule
    get() = module {
//        single {
//            ImageLoader.Builder(get())
//                .components {
//                    add(
//                        KtorNetworkFetcherFactory(
//                            httpClient = get<HttpClient>()
//                        )
//                    )
//                }
//                .logger(DebugLogger())
//                .build()
//        }
    }