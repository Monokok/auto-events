package ru.autoevents.auto_events_client

import ru.autoevents.auto_events_client.core.network.di.networkModule
import ru.autoevents.auto_events_client.feature.home.di.homeScreensModule


private val featureModules
    get() = listOf(
        homeScreensModule,
    )

private val coreModules
    get() = listOf(
        networkModule,
    )

val appModules
    get() = listOf(
        coreModules,
        featureModules,
    ).flatten()