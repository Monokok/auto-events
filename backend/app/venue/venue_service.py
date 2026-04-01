from database.uow import IUnitOfWork
from venue.venue_schemas import CityDTO


class VenueService:
    uow: IUnitOfWork

    def __init__(self, uow: IUnitOfWork) -> None:
        self.uow = uow

    async def get_cities(self) -> list[CityDTO]:
        async with self.uow:
            cities = await self.uow.venues.get_all_cities()

        return [CityDTO.model_validate(c) for c in cities]
