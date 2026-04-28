from typing import Annotated

from dishka import FromDishka
from dishka.integrations.fastapi import DishkaRoute
from fastapi import APIRouter, Depends, HTTPException, status
from fastapi_filter.base.filter import FilterDepends

from auth.auth_router import CurrentUser
from event.event_filter import EventFilter, EventTypeFilter
from event.event_schemas import EventCreateDTO, EventShortDTO, EventTypeDTO
from event.event_service import EventService
from utils.exceptions import NotFound
from utils.pagination import Page, PaginationParams

event_router = APIRouter(prefix="/events", tags=["Event"], route_class=DishkaRoute)


@event_router.get(
    "", response_model=Page[EventShortDTO], summary="Get actual events list"
)
async def get_actual_events(
    event_service: FromDishka[EventService],
    filter: Annotated[EventFilter, FilterDepends(EventFilter)],
    pagination_params: Annotated[PaginationParams, Depends()],
):
    return await event_service.get_actual_events(filter, pagination_params)


@event_router.get(
    "/types", response_model=list[EventTypeDTO], summary="Get event types"
)
async def get_event_types(
    filter: Annotated[EventTypeFilter, FilterDepends(EventTypeFilter)],
    event_service: FromDishka[EventService],
):
    return await event_service.get_event_types(filter)


@event_router.get(
    "/{event_id}",
    response_model=EventShortDTO,
    summary="Get event by id",
)
async def get_event_by_id(
    event_id: int,
    event_service: FromDishka[EventService],
):
    event = await event_service.get_event_by_id(event_id)
    if event is None:
        raise HTTPException(status_code=404, detail="Event not found")
    return event


@event_router.post(
    "",
    response_model=EventShortDTO,
    summary="Create event",
)
async def create_event(
    create_schema: EventCreateDTO,
    user: CurrentUser,
    event_service: FromDishka[EventService],
):
    try:
        return await event_service.create_event(create_schema, user.id)
    except NotFound as e:
        raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail=e.message)
