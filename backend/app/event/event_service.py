from database.uow import IUnitOfWork
from event.event_filter import EventFilter, EventTypeFilter
from event.event_schemas import EventCreateDTO, EventShortDTO, EventTypeDTO
from models import Event
from utils.exceptions import NotFound
from utils.pagination import Page, PaginationParams


class EventService:
    uow: IUnitOfWork

    def __init__(self, uow: IUnitOfWork) -> None:
        self.uow = uow

    async def get_actual_events(
        self, filter: EventFilter, pagination_params: PaginationParams
    ) -> Page[EventShortDTO]:

        async with self.uow:
            events_page = await self.uow.events.get_all_actual_page(
                filter, pagination_params
            )

        events_schemas = [
            EventShortDTO.model_validate(event) for event in events_page.items
        ]
        return Page(**events_page.dump_params(), items=events_schemas)

    async def get_event_types(self, filter: EventTypeFilter) -> list[EventTypeDTO]:
        async with self.uow:
            event_types = await self.uow.events.get_event_types(filter)

        return [EventTypeDTO.model_validate(et) for et in event_types]

    async def get_event_by_id(
        self, event_id: int, update_views_count: bool = True
    ) -> EventShortDTO | None:
        async with self.uow:
            event = await self.uow.events.get_by_id(event_id)
            if event is None:
                return None
            if update_views_count:
                event = await self.uow.events.increment_views_count(event.id)
                await self.uow.commit()

        return EventShortDTO.model_validate(event)

    async def create_event(
        self, create_schema: EventCreateDTO, owner_id: int
    ) -> EventShortDTO:
        async with self.uow:
            venue = await self.uow.venues.get_venue_by_id(create_schema.venue_id)
            if venue is None:
                raise NotFound(f"Venue (id={create_schema.venue_id}) not found")

            event_type = await self.uow.events.get_type_by_id(create_schema.type_id)
            if event_type is None:
                raise NotFound(f"Event type (id={create_schema.type_id}) not found")

            new_event = Event(**create_schema.model_dump(), owner_id=owner_id)
            await self.uow.events.create(new_event)
            await self.uow.commit()
            new_event = await self.uow.events.get_by_id(new_event.id)

        return EventShortDTO.model_validate(new_event)
