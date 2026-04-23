package ru.autoevents.auto_events_client.core.ui.extensions

fun Int.secondsToMinutes(): String {
    val minutes = this / 60
    val seconds = this % 60
    return "$minutes:$seconds"
}

fun String?.setDashText(): String =
    this ?: "-"