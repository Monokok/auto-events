from datetime import datetime

from pydantic import AliasPath, BaseModel, ConfigDict, Field, HttpUrl

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
