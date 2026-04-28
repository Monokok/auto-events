from typing import Annotated

from dishka import FromDishka
from dishka.integrations.fastapi import DishkaRoute
from fastapi import APIRouter
from fastapi_filter.base.filter import FilterDepends

from event.event_schemas import EventTypeDTO
from venue.venue_filter import VenueFilter
from venue.venue_schemas import VenueDTO
from venue.venue_service import VenueService

venue_router = APIRouter(tags=["Venue"], route_class=DishkaRoute)


@venue_router.get("/cities", response_model=list[EventTypeDTO], summary="Get cities")
async def get_cities(venue_service: FromDishka[VenueService]):
    return await venue_service.get_cities()


@venue_router.get("/venues", response_model=list[VenueDTO], summary="Get venues")
async def get_venues(
    filter: Annotated[VenueFilter, FilterDepends(VenueFilter)],
    venue_service: FromDishka[VenueService],
):
    return await venue_service.get_venues(filter)
