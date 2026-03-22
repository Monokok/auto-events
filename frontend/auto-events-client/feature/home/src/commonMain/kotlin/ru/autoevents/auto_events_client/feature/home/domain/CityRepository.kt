package ru.autoevents.auto_events_client.feature.home.domain

import ru.autoevents.auto_events_client.core.network.api.CityApi
import ru.autoevents.auto_events_client.feature.home.data.model.CityUi

/**
 * Репозиторий городов
 *
 * @property api объект для получения событий через запрос к нему
 * @return  [List]<[CityUi]>
 */
class CityRepository(private val api: CityApi) {
    suspend fun fetchCities(): Result<List<CityUi>> = runCatching {
        api.getCities().mapToUi()
    }
}