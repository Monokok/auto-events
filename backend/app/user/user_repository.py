from sqlalchemy import or_, select, update
from sqlalchemy.exc import IntegrityError
from sqlalchemy.ext.asyncio import AsyncSession

from models import User
from utils.exceptions import DbIncorrectData


class UserRepository:
    def __init__(self, session: AsyncSession):
        self.session = session

    async def get_by_id(self, user_id: int) -> User | None:
        query = select(User).where(User.id == user_id)
        return await self.session.scalar(query)

    async def get_by_username(self, username: str) -> User | None:
        query = select(User).where(User.username == username)
        return await self.session.scalar(query)

    async def get_by_username_or_email(self, username: str, email: str) -> User | None:
        query = select(User).where(or_(User.username == username, User.email == email))
        return await self.session.scalar(query)

    async def get_all(self, limit: int, offset: int) -> list[User]:
        query = select(User).order_by(User.username).offset(offset).limit(limit)
        result = await self.session.scalars(query)
        return list(result.all())

    async def create_user(self, user: User) -> User:
        self.session.add(user)
        await self.session.commit()
        return user

    async def update_user(self, update_dict: dict) -> None:
        try:
            await self.session.execute(update(User), [update_dict])
            await self.session.commit()
        except IntegrityError as e:
            raise DbIncorrectData from e
