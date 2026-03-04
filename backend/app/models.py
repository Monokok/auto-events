from enum import Enum

from sqlalchemy import String
from sqlalchemy.orm import DeclarativeBase, Mapped, mapped_column


class Base(DeclarativeBase):
    """
    Базовая модель для ORM SQLAlchemy
    """


class Role(str, Enum):
    user = "user"
    admin = "admin"


class User(Base):
    """
    Модель таблицы пользователей
    """

    __tablename__ = "users"
    id: Mapped[int] = mapped_column(primary_key=True)
    username: Mapped[str] = mapped_column(
        String(length=20), unique=True, nullable=False
    )
    role: Mapped[Role] = mapped_column(default="user")
    email: Mapped[str] = mapped_column(String(length=30), unique=True, nullable=False)
    password: Mapped[str]
