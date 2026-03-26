package ru.autoevents.auto_events_client.feature.home.data.useCases

import ru.autoevents.auto_events_client.feature.home.data.model.CityUi
import ru.autoevents.auto_events_client.feature.home.domain.EventRepository

/**
 * Получает список всех городов из репозитория.
 *
 * Данный use case инкапсулирует бизнес-операцию загрузки городов для UI.
 * Возвращает [Result] с преобразованными [CityUi] моделями или ошибку.
 *
 * @property repository репозиторий событий для получения данных
 * @return [Result]<[List]<[CityUi]>> результат операции
 */
class GetCitiesUseCase(val repository: EventRepository) {
    suspend operator fun invoke(): Result<List<CityUi>> = repository.fetchCities()
}