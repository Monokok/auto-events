from models import Event, EventType, Venue
from utils.base_filter import BaseFilter


class EventFilter(BaseFilter):
    search: str | None = None
    city_id: int | None = None
    type_id: int | None = None

    order_by: list[str] | None = ["-views_count"]

    class Constants(BaseFilter.Constants):
        model = Event
        search_model_fields = ["title", "description"]
        allow_sort_fields = {"title", "views_count", "starts_at", "ends_at"}

        overwrite_filter_fields = {
            "city_id": lambda value: Venue.city_id == value,
            "type_id": lambda value: Event.type_id == value,
        }


class EventTypeFilter(BaseFilter):
    city_id: int | None = None

    order_by: list[str] | None = None

    class Constants(BaseFilter.Constants):
        model = EventType
        allow_sort_fields = {"name"}
        overwrite_filter_fields = {
            "city_id": lambda value: Venue.city_id == value,
        }
