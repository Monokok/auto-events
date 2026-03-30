from typing import Annotated

from fastapi import APIRouter, Depends, HTTPException
from fastapi_filter.base.filter import FilterDepends

from event.event_filter import EventFilter
from event.event_schemas import EventShortDTO, EventTypeDTO
from event.event_service import EventService
from utils.dependencies import UOW
from utils.pagination import Page, PaginationParams

event_router = APIRouter(prefix="/events", tags=["Event"])


@event_router.get(
    "", response_model=Page[EventShortDTO], summary="Get actual events list"
)
async def get_actual_events(
    uow: UOW,
    filter: Annotated[EventFilter, FilterDepends(EventFilter)],
    pagination_params: Annotated[PaginationParams, Depends()],
):
    return await EventService.get_actual_events(uow, filter, pagination_params)


@event_router.get(
    "/types", response_model=list[EventTypeDTO], summary="Get event types"
)
async def get_event_types(uow: UOW):
    return await EventService.get_event_types(uow)


@event_router.get(
    "/{event_id}",
    response_model=EventShortDTO,
    summary="Get event by id",
)
async def get_event_by_id(event_id: int, uow: UOW):
    event = await EventService.get_event_by_id(uow, event_id)
    if event is None:
        raise HTTPException(status_code=404, detail="Event not found")
    return event