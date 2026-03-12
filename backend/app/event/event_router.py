from fastapi import APIRouter

from event.event_schemas import EventListDTO
from event.event_service import EventService

event_router = APIRouter(prefix="/event", tags=["Event"])


@event_router.get("", response_model=EventListDTO, summary="Get events list")
async def get_events():
    return await EventService.get_events()