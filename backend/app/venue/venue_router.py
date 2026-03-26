from fastapi import APIRouter

from event.event_schemas import EventTypeDTO
from utils.dependencies import UOW
from venue.venue_service import VenueService

venue_router = APIRouter(tags=["Venue"])


@venue_router.get("/cities", response_model=list[EventTypeDTO], summary="Get cities")
async def get_cities(uow: UOW):
    return await VenueService.get_cities(uow)
