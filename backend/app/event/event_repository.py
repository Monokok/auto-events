from datetime import UTC, datetime
from typing import cast

from sqlalchemy import Select, and_, select
from sqlalchemy.ext.asyncio import AsyncSession
from sqlalchemy.orm import contains_eager

from event.event_filter import EventFilter
from models import Event, EventStatusEnum
from utils.pagination import Page, PaginationParams, paginate


class EventRepository:
    def __init__(self, session: AsyncSession):
        self.session = session

    async def get_all_actual_page(
        self, filter: EventFilter, pagination_params: PaginationParams
    ) -> Page[Event]:
        query = (
            select(Event)
            .join(Event.venue)
            .where(
                and_(
                    Event.status == EventStatusEnum.published,
                    Event.ends_at >= datetime.now(UTC),
                )
            )
            .options(contains_eager(Event.venue))
        )
        query = filter.filter(query)
        query = cast(Select[tuple[Event]], filter.sort(query))

        return await paginate(query, self.session, pagination_params)
