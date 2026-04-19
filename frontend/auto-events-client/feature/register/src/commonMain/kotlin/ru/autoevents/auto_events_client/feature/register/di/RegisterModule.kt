package ru.autoevents.auto_events_client.feature.register.di

import org.koin.dsl.module
import ru.autoevents.auto_events_client.feature.register.data.RegisterRepository
import ru.autoevents.auto_events_client.feature.register.domain.useCases.RegisterUseCase
import ru.autoevents.auto_events_client.feature.register.ui.register.RegisterScreenModel

val registerScreensModule
    get() = module {
        single { RegisterRepository(get()) }
        single { RegisterUseCase(get()) }

        factory { RegisterScreenModel(get()) }
    }