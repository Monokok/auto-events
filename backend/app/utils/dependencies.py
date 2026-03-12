from typing import Annotated

from fastapi import Depends
from pydantic import BaseModel, Field

from database.uow import UnitOfWork


class Paginator(BaseModel):
    """
    Класс-шаблон для пагинации в эндпоинтах
    """

    limit: Annotated[int, Field(ge=0)]
    offset: Annotated[int, Field(ge=0)]

    def __init__(self, limit: int = 30, offset: int = 0):
        super().__init__(limit=limit, offset=offset)


async def _get_uow():
    yield UnitOfWork()


UOW = Annotated[UnitOfWork, Depends(_get_uow)]
