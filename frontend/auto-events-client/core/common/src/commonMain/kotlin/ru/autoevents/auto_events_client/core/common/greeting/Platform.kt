package ru.autoevents.auto_events_client.core.common.greeting

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform