from datetime import datetime
from enum import Enum

from pydantic import BaseModel, Field, HttpUrl


class EventType(str, Enum):
    drift = "drift"
    rally = "rally"
    exhibition = "exhibition"
    meet = "meet"


class EventStatus(str, Enum):
    draft = "draft"
    published = "published"
    moderated = "moderated"


class EventShortDTO(BaseModel):
    id: int
    title: str = Field(max_length=100)
    description: str = Field(max_length=500)
    event_type: EventType
    region: str = Field(max_length=100)
    city: str = Field(max_length=100)
    venue: str = Field(max_length=150)
    starts_at: datetime
    ends_at: datetime
    is_free: bool
    ticket_url: HttpUrl | None = None
    registration_url: HttpUrl | None = None
    status: EventStatus


class EventListDTO(BaseModel):
    items: list[EventShortDTO]
    total: int