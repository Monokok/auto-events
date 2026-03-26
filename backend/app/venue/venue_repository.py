from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession

from models import City


class VenueRepository:
    def __init__(self, session: AsyncSession):
        self.session = session

    async def get_all_cities(self) -> list[City]:
        query = select(City).order_by(City.name)
        result = await self.session.scalars(query)
        return list(result.all())
