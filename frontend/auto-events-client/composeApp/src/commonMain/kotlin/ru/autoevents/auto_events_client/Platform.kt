package ru.autoevents.auto_events_client

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform