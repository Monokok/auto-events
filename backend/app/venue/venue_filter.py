from models import Venue
from utils.base_filter import BaseFilter


class VenueFilter(BaseFilter):
    search: str | None = None
    city_id: int | None = None

    order_by: list[str] | None = ["name"]

    class Constants(BaseFilter.Constants):
        model = Venue
        allow_sort_fields = {"name", "id"}
        search_model_fields = ["name"]
