package ru.autoevents.auto_events_client.core.common.greeting


class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}