package ru.autoevents.auto_events_client.feature.home.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.autoevents.auto_events_client.core.network.api.EventApi
import ru.autoevents.auto_events_client.feature.home.domain.model.EventUi

class EventsPagingSource(
    private val api: EventApi,
    private val cityId: Int?,
    private val typeId: Int?,
) : PagingSource<Int, EventUi>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventUi> {
        val nextOffset = params.key ?: 1
        val result = api.getEvents(cityId, typeId, nextOffset, PAGE_SIZE)
        val mappedList = result.mapToUi()
        return LoadResult.Page(
            data = mappedList,
            prevKey = null,
            nextKey = if (mappedList.size >= PAGE_SIZE) nextOffset + 1 else null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, EventUi>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_SIZE) ?: anchorPage?.nextKey?.minus(PAGE_SIZE)
        }
    }

    companion object {

        const val PAGE_SIZE: Int = 10
        const val PAGE_PREFETCH: Int = 5
    }
}