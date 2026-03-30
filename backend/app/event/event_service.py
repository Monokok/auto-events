from database.uow import UnitOfWork
from event.event_filter import EventFilter
from event.event_schemas import EventShortDTO, EventTypeDTO
from utils.pagination import Page, PaginationParams


class EventService:
    @staticmethod
    async def get_actual_events(
        uow: UnitOfWork, filter: EventFilter, pagination_params: PaginationParams
    ) -> Page[EventShortDTO]:

        async with uow:
            events_page = await uow.events.get_all_actual_page(
                filter, pagination_params
            )

        events_schemas = [
            EventShortDTO.model_validate(event) for event in events_page.items
        ]
        return Page(**events_page.dump_params(), items=events_schemas)

    @staticmethod
    async def get_event_types(uow: UnitOfWork) -> list[EventTypeDTO]:
        async with uow:
            event_types = await uow.events.get_event_types()

        return [EventTypeDTO.model_validate(et) for et in event_types]
    
    
    @staticmethod
    async def get_event_by_id(
        uow: UnitOfWork, event_id: int
    ) -> EventShortDTO | None:
        async with uow:
            event = await uow.events.get_by_id(event_id)

        if event is None:
            return None

        return EventShortDTO.model_validate(event)
