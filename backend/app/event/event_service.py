from datetime import datetime

from event.event_schemas import EventListDTO, EventShortDTO, EventStatus, EventType


class EventService:
    @staticmethod
    async def get_events() -> EventListDTO:
        events = [
            EventShortDTO(
                id=1,
                title="Drift Weekend Moscow",
                description="Двухдневный дрифт-фестиваль с любительскими заездами и шоу-программой.",
                event_type=EventType.drift,
                region="Москва",
                city="Москва",
                venue="ADM Raceway",
                starts_at=datetime(2026, 4, 18, 11, 0, 0),
                ends_at=datetime(2026, 4, 19, 20, 0, 0),
                is_free=False,
                ticket_url="https://example.com/tickets/drift-weekend-moscow",
                registration_url="https://example.com/register/drift-weekend-moscow",
                status=EventStatus.published,
            ),
            EventShortDTO(
                id=2,
                title="Ралли-спринт Казань",
                description="Любительский ралли-спринт для участников на серийных и подготовленных автомобилях.",
                event_type=EventType.rally,
                region="Республика Татарстан",
                city="Казань",
                venue="Казань Ринг",
                starts_at=datetime(2026, 5, 2, 9, 30, 0),
                ends_at=datetime(2026, 5, 2, 18, 0, 0),
                is_free=True,
                ticket_url=None,
                registration_url="https://example.com/register/rally-kazan",
                status=EventStatus.published,
            ),
            EventShortDTO(
                id=3,
                title="Автовыставка Урал Мотор Шоу",
                description="Выставка тюнингованных авто, ретро-машин и кастомных проектов.",
                event_type=EventType.exhibition,
                region="Свердловская область",
                city="Екатеринбург",
                venue="Екатеринбург-Экспо",
                starts_at=datetime(2026, 6, 15, 10, 0, 0),
                ends_at=datetime(2026, 6, 15, 19, 0, 0),
                is_free=False,
                ticket_url="https://example.com/tickets/ural-motor-show",
                registration_url=None,
                status=EventStatus.published,
            ),
        ]

        return EventListDTO(items=events, total=len(events))