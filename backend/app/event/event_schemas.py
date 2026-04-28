from datetime import UTC, datetime
from typing import Any, Self

from pydantic import (
    AliasPath,
    BaseModel,
    ConfigDict,
    Field,
    HttpUrl,
    PositiveInt,
    field_serializer,
    model_validator,
)

from models import EventStatusEnum


class EventShortDTO(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: int
    title: str
    description: str
    status: EventStatusEnum
    event_type: str = Field(validation_alias=AliasPath("type", "name"))
    city: str = Field(validation_alias=AliasPath("venue", "city", "name"))
    venue: str = Field(validation_alias=AliasPath("venue", "name"))
    starts_at: datetime
    ends_at: datetime

    link: HttpUrl | None = None
    picture_url: HttpUrl | None = None
    views_count: int
    participant_price: int | None
    viewer_price: int | None


class EventTypeDTO(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    id: int
    name: str


class EventCreateDTO(BaseModel):
    model_config = ConfigDict(from_attributes=True)

    title: str = Field(min_length=1, max_length=32)
    description: str = Field(min_length=1, max_length=256)
    link: HttpUrl | None = None
    picture_url: HttpUrl | None = None
    type_id: PositiveInt
    venue_id: PositiveInt

    starts_at: datetime
    ends_at: datetime

    participant_price: int | None = None
    viewer_price: int | None = None

    @model_validator(mode="after")
    def check_dates(self) -> Self:
        if self.starts_at > self.ends_at:
            raise ValueError("Event starts_at can't be after ends_at.")
        elif datetime.now(UTC) >= self.ends_at:
            raise ValueError("Event ends_at must be after now.")
        return self

    @field_serializer("link", "picture_url", mode="plain")
    def serialize_link(self, value: Any) -> Any:
        if isinstance(value, HttpUrl):
            return str(value)
        return value
