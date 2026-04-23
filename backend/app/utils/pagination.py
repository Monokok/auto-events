from typing import Annotated

from fastapi import Query
from pydantic import BaseModel, ConfigDict, Field, PositiveInt
from sqlalchemy import select
from sqlalchemy.ext.asyncio import AsyncSession
from sqlalchemy.sql import Select, func


class Page[T](BaseModel):
    model_config = ConfigDict(arbitrary_types_allowed=True, from_attributes=True)

    # элементы страницы
    items: list[T]
    # текущая страница
    page: PositiveInt = Field(1, ge=1)
    # количество записей на страницу
    size: PositiveInt = Field(10, ge=1)
    # полное количество объектов
    total: int = Field(ge=0)

    def dump_params(self) -> dict:
        return self.model_dump(exclude={"items"})


class PaginationParams(BaseModel):
    page: Annotated[int, Query(ge=1, description="Номер страницы")] = 1
    size: Annotated[int, Query(ge=1, le=100, description="Размер страницы")] = 10


async def paginate[T](
    query: Select[tuple[T]],
    session: AsyncSession,
    pagination_params: PaginationParams,
) -> Page[T]:
    """Выполняет целевой запрос с применением пагинации"""
    total = (
        await session.scalars(select(func.count()).select_from(query.subquery()))
    ).one()

    page, size = pagination_params.page, pagination_params.size
    items = (await session.scalars(query.offset((page - 1) * size).limit(size))).all()

    return Page(
        items=list(items),
        total=total,
        **pagination_params.model_dump(),
    )
