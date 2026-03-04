from sqlalchemy.exc import InterfaceError

from database.database import DatabasePgs
from user.user_repository import UserRepository
from utils.exceptions import DbConnectionError


class UnitOfWork:
    def __init__(self):
        self.session_factory = DatabasePgs.session_factory

    async def __aenter__(self):
        self.session = self.session_factory()
        self.users = UserRepository(self.session)

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
