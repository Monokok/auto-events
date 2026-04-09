from database.uow import IUnitOfWork
from event.event_filter import EventFilter
from event.event_schemas import EventShortDTO, EventTypeDTO
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

    async def get_event_types(self) -> list[EventTypeDTO]:
        async with self.uow:
            event_types = await self.uow.events.get_event_types()

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
