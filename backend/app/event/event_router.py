from typing import Annotated

from fastapi import APIRouter, Depends
from fastapi_filter.base.filter import FilterDepends

from event.event_filter import EventFilter
from event.event_schemas import EventShortDTO
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
