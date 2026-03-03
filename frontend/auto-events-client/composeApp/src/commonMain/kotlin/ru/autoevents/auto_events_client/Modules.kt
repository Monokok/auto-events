package ru.autoevents.auto_events_client

import ru.autoevents.auto_events_client.feature.home.di.homeScreensModule


private val featureModules
    get() = listOf(
        homeScreensModule,
    )

val appModules
    get() = listOf(
        featureModules,
    ).flatten()