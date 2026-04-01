from dishka import FromDishka
from dishka.integrations.fastapi import DishkaRoute
from fastapi import APIRouter

from event.event_schemas import EventTypeDTO
from venue.venue_service import VenueService

venue_router = APIRouter(tags=["Venue"], route_class=DishkaRoute)


@venue_router.get("/cities", response_model=list[EventTypeDTO], summary="Get cities")
async def get_cities(venue_service: FromDishka[VenueService]):
    return await venue_service.get_cities()
