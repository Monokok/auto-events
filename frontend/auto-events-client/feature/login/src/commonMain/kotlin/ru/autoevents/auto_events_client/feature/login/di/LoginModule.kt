package ru.autoevents.auto_events_client.feature.login.di

import org.koin.dsl.module
import ru.autoevents.auto_events_client.feature.login.data.AuthorizationRepository
import ru.autoevents.auto_events_client.feature.login.data.UsersRepository
import ru.autoevents.auto_events_client.feature.login.domain.useCases.AuthorizationUseCase
import ru.autoevents.auto_events_client.feature.login.ui.login.LoginScreenModel

val loginScreensModule
    get() = module {
        single { AuthorizationRepository(get()) }
        single { UsersRepository(get()) }
        single { AuthorizationUseCase(get(), get()) }
//        single { LoginScreenModel(get()) }

        factory { LoginScreenModel(get()) }
    }