package ru.autoevents.auto_events_client.feature.home.data.model

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDateTime

/**
 * UI-модель события для отображения в календаре.
 *
 * @Immutable предназначена для Compose — неизменяема, оптимизирована для recomposition.
 * Содержит все данные события, преобразованные из [Event] доменной модели.
 *
 * @property id уникальный идентификатор события
 * @property title название/заголовок события
 * @property description подробное описание
 * @property eventType тип события (ралли, дрифт, выставка)
 * @property region регион проведения
 * @property city город проведения
 * @property venue точное место проведения
 * @property startsAt время начала (nullable)
 * @property endsAt время окончания (nullable)
 * @property isFree бесплатное событие
 * @property ticketUrl ссылка на покупку билетов
 * @property registrationUrl ссылка на регистрацию
 * @property status статус события (planned, cancelled, finished)
 */
@Immutable
data class EventUi(
    val id: Int,
    val title: String,
    val description: String,
    val eventType: String,
    val region: String,
    val city: String,
    val venue: String,
    val startsAt: LocalDateTime?,
    val endsAt: LocalDateTime?,
    val isFree: Boolean,
    val ticketUrl: String?,
    val registrationUrl: String?,
    val status: String
)
