from abc import ABC, abstractmethod

from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from models import City, Venue


class IVenueRepository(ABC):
    @abstractmethod
    async def get_all_cities(self) -> list[City]: ...

    @abstractmethod
    async def get_venue_by_id(self, id: int) -> Venue | None: ...


class VenueRepository(IVenueRepository):
    def __init__(self, session: AsyncSession):
        self.session = session

    async def get_all_cities(self) -> list[City]:
        query = select(City).order_by(City.name)
        result = await self.session.scalars(query)
        return list(result.all())

    async def get_venue_by_id(self, id: int) -> Venue | None:
        query = select(Venue).where(Venue.id == id)
        return await self.session.scalar(query)
