from abc import ABC, abstractmethod

from sqlalchemy.exc import InterfaceError

from database.database import DatabasePgs
from event.event_repository import EventRepository, IEventRepository
from user.user_repository import IUserRepository, UserRepository
from utils.exceptions import DbConnectionError
from venue.venue_repository import IVenueRepository, VenueRepository


class IUnitOfWork(ABC):
    users: IUserRepository
    events: IEventRepository
    venues: IVenueRepository

    @abstractmethod
    async def __aenter__(self): ...

    @abstractmethod
    async def __aexit__(self, exc_type, exc, tb): ...

    @abstractmethod
    async def commit(self): ...

    @abstractmethod
    async def rollback(self): ...


class UnitOfWork(IUnitOfWork):
    def __init__(self):
        self.session_factory = DatabasePgs.session_factory

    async def __aenter__(self):
        self.session = self.session_factory()
        self.users = UserRepository(self.session)
        self.events = EventRepository(self.session)
        self.venues = VenueRepository(self.session)

    async def __aexit__(self, exc_type, exc, tb):
        await self.session.close()
        if (
            exc_type is OSError
            or exc_type is ConnectionError
            or exc_type is InterfaceError
            or exc_type is ConnectionRefusedError
        ):
            raise DbConnectionError from exc

    async def commit(self):
        await self.session.commit()

    async def rollback(self):
        await self.session.rollback()
