from sqlalchemy import insert, text
from sqlalchemy.exc import InterfaceError
from sqlalchemy.ext.asyncio import (
    AsyncEngine,
    async_sessionmaker,
    create_async_engine,
)

from config import settings
from utils.exceptions import DbConnectionError


class DatabasePgs:
    engine: AsyncEngine
    session_factory: async_sessionmaker

    @staticmethod
    async def init_db():
        try:
            test_engine = create_async_engine(
                url=str(settings.POSTGRES_DB_HOST_URI),
                echo=False,
            )
            is_db_exist = await DatabasePgs.check_db_existing(test_engine)
            await test_engine.dispose()
            DatabasePgs.engine = create_async_engine(
                url=str(settings.POSTGRES_DB_URI), echo=settings.DEBUG
            )
            if not is_db_exist:
                await DatabasePgs.create_and_init_tables(DatabasePgs.engine)

            DatabasePgs.session_factory = async_sessionmaker(
                DatabasePgs.engine, expire_on_commit=False
            )
        except (OSError, InterfaceError, ConnectionError, ConnectionRefusedError) as e:
            raise DbConnectionError from e

    @staticmethod
    async def create_and_init_tables(engine: AsyncEngine):
        from models import Base, User

        async with engine.connect() as conn:
            await conn.run_sync(Base.metadata.create_all)
            await conn.execute(
                insert(User),
                [
                    {
                        "username": "admin",
                        "email": "admin@example.com",
                        "role": "admin",
                        "password": settings.ADMIN_PASSWORD,
                    },
                ],
            )
            await conn.commit()

    @staticmethod
    async def check_db_existing(engine: AsyncEngine):
        async with engine.connect() as conn:
            res = await conn.execute(text("select datname from pg_database;"))
            exist_db = res.scalars().all()
            if settings.DB_NAME not in exist_db:
                await conn.execute(text("commit"))
                await conn.execute(text(f"CREATE DATABASE {settings.DB_NAME}"))
                return False
            return True
