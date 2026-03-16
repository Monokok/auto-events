from datetime import datetime
from enum import StrEnum

from sqlalchemy import DateTime, ForeignKey, String
from sqlalchemy.orm import DeclarativeBase, Mapped, mapped_column, relationship


class Base(DeclarativeBase):
    """
    Базовая модель для ORM SQLAlchemy
    """


class EventStatus(StrEnum):
    draft = "draft"
    published = "published"
    moderated = "moderated"


class Role(StrEnum):
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


class City(Base):
    """
    Модель таблицы городов
    """

    __tablename__ = "cities"
    id: Mapped[int] = mapped_column(primary_key=True)
    name: Mapped[str] = mapped_column(String(100))

    venues: Mapped[list[Venue]] = relationship(back_populates="city")


class Venue(Base):
    """
    Модель таблицы мест событий
    """

    __tablename__ = "venues"
    id: Mapped[int] = mapped_column(primary_key=True)
    name: Mapped[str] = mapped_column(String(100))
    city_id: Mapped[int] = mapped_column(ForeignKey("cities.id"))

    city: Mapped[City] = relationship(back_populates="venues", lazy="joined")
    events: Mapped[list[Event]] = relationship(back_populates="venue")


class EventType(Base):
    """
    Модель таблицы типов событий
    """

    __tablename__ = "event_types"
    id: Mapped[int] = mapped_column(primary_key=True)
    name: Mapped[str] = mapped_column(String(100))

    events: Mapped[list[Event]] = relationship(back_populates="type")


class Event(Base):
    """
    Модель таблицы событий
    """

    __tablename__ = "events"
    id: Mapped[int] = mapped_column(primary_key=True)
    title: Mapped[str] = mapped_column(String(100))
    description: Mapped[str] = mapped_column(String(500))
    link: Mapped[str | None] = mapped_column(String(200))
    status: Mapped[EventStatus]
    views_count: Mapped[int] = mapped_column(default=0)
    type_id: Mapped[int] = mapped_column(ForeignKey("event_types.id"))
    venue_id: Mapped[int] = mapped_column(ForeignKey("venues.id"))

    participant_price: Mapped[int | None]
    viewer_price: Mapped[int | None]

    starts_at: Mapped[datetime] = mapped_column(DateTime(timezone=True))
    ends_at: Mapped[datetime] = mapped_column(DateTime(timezone=True))

    type: Mapped[EventType] = relationship(back_populates="events", lazy="joined")
    venue: Mapped[Venue] = relationship(back_populates="events", lazy="joined")
