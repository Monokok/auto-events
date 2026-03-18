package ru.autoevents.auto_events_client.feature.home.data.useCases

import ru.autoevents.auto_events_client.feature.home.data.model.EventUi
import ru.autoevents.auto_events_client.feature.home.domain.EventRepository

/**
 * Получает список всех доступных событий из репозитория.
 *
 * Данный use case инкапсулирует бизнес-операцию загрузки событий для UI.
 * Возвращает [Result] с преобразованными [EventUi] моделями или ошибку.
 *
 * @property repository репозиторий событий для получения данных
 * @return [Result]<[List]<[EventUi]>> результат операции
 */
class GetEventListUseCase(val repository: EventRepository) {
    suspend operator fun invoke(): Result<List<EventUi>> =
        repository.fetchEvents()
}