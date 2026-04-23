from abc import ABC, abstractmethod
from datetime import UTC, datetime
from typing import cast

from sqlalchemy import Select, and_, select, update
from sqlalchemy.ext.asyncio import AsyncSession
from sqlalchemy.orm import contains_eager, joinedload

from event.event_filter import EventFilter, EventTypeFilter
from models import Event, EventStatusEnum, EventType, Venue
from utils.pagination import Page, PaginationParams, paginate


class IEventRepository(ABC):
    @abstractmethod
    async def get_all_actual_page(
        self, filter: EventFilter, pagination_params: PaginationParams
    ) -> Page[Event]: ...

    @abstractmethod
    async def get_event_types(self, filter: EventTypeFilter) -> list[EventType]: ...

    @abstractmethod
    async def get_by_id(self, event_id: int) -> Event | None: ...

    @abstractmethod
    async def increment_views_count(self, event_id: int) -> Event: ...


class EventRepository(IEventRepository):
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

    async def get_event_types(self, filter: EventTypeFilter) -> list[EventType]:
        query = select(EventType)

        if filter.city_id is not None:
            query = query.join(EventType.events).join(Event.venue)

        query = filter.filter(query)
        query = filter.sort(query)

        result = await self.session.scalars(query)
        return list(result.unique())

    async def get_by_id(self, event_id: int) -> Event | None:
        query = (
            select(Event)
            .where(Event.id == event_id)
            .options(
                joinedload(Event.type),
                joinedload(Event.venue).joinedload(Venue.city),
            )
        )
        result = await self.session.scalar(query)
        return result

    async def increment_views_count(self, event_id: int) -> Event:
        query = (
            update(Event)
            .where(Event.id == event_id)
            .values(views_count=Event.views_count + 1)
            .returning(Event)
        )
        return (await self.session.execute(query)).scalar_one()
