package ru.autoevents.auto_events_client

import ru.autoevents.auto_events_client.core.network.di.networkModule
import ru.autoevents.auto_events_client.core.ui.di.uiModule
import ru.autoevents.auto_events_client.feature.home.di.homeScreensModule
import ru.autoevents.auto_events_client.feature.login.di.loginScreensModule
import ru.autoevents.auto_events_client.feature.profile.di.profileScreensModule
import ru.autoevents.auto_events_client.feature.register.di.registerScreensModule


private val featureModules
    get() = listOf(
        homeScreensModule,
        profileScreensModule,
        loginScreensModule,
        registerScreensModule
    )

private val coreModules
    get() = listOf(
        networkModule,
        uiModule,
    )

val appModules
    get() = listOf(
        coreModules,
        featureModules,
    ).flatten()