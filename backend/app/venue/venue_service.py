from database.uow import IUnitOfWork
from venue.venue_filter import VenueFilter
from venue.venue_schemas import CityDTO, VenueDTO


class VenueService:
    uow: IUnitOfWork

    def __init__(self, uow: IUnitOfWork) -> None:
        self.uow = uow

    async def get_cities(self) -> list[CityDTO]:
        async with self.uow:
            cities = await self.uow.venues.get_all_cities()

        return [CityDTO.model_validate(c) for c in cities]

    async def get_venues(self, filter: VenueFilter) -> list[VenueDTO]:
        async with self.uow:
            venues = await self.uow.venues.get_all_venues(filter)

        return [VenueDTO.model_validate(v) for v in venues]
