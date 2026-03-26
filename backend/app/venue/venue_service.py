from database.uow import UnitOfWork
from venue.venue_schemas import CityDTO


class VenueService:
    @staticmethod
    async def get_cities(uow: UnitOfWork) -> list[CityDTO]:
        async with uow:
            cities = await uow.venues.get_all_cities()

        return [CityDTO.model_validate(c) for c in cities]
