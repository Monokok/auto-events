from collections import defaultdict
from collections.abc import Callable
from typing import Any

from fastapi_filter.contrib.sqlalchemy import Filter
from pydantic import ValidationInfo, field_validator
from sqlalchemy import ColumnExpressionArgument
from sqlalchemy.orm import Query
from sqlalchemy.sql import Select


class BaseFilter(Filter):
    class Constants(Filter.Constants):
        allow_sort_fields: set[str] | None = None
        overwrite_sort_fields: dict[str, ColumnExpressionArgument] = {}
        overwrite_filter_fields: dict[
            str, Callable[[Any], ColumnExpressionArgument]
        ] = {}

    @field_validator("*", mode="before", check_fields=False)
    def validate_order_by(cls, value: Any, field: ValidationInfo):
        """Модифицируем валидацию поля сортировки.
        - Добавляем возможность ограничить набор полей для сортировки
          (allow_sort_fields).
        - Для выбранных в allow_sort_fields полей отключается проверка
          на наличие поля в модели.
        """
        if field.field_name != cls.Constants.ordering_field_name:
            return value

        if not value:
            return None

        cls._restrict_sortable_fields(value)

        field_name_usages = defaultdict(list)
        duplicated_field_names = set()

        for field_name_with_direction in value:
            field_name = field_name_with_direction.replace("-", "").replace("+", "")

            # Добавляем возможность пропускать валидацию поля из ordering_field_name
            if not hasattr(cls.Constants.model, field_name) and (
                cls.Constants.allow_sort_fields is None
                or field_name not in cls.Constants.allow_sort_fields
            ):
                raise ValueError(f"{field_name} is not a valid ordering field.")

            field_name_usages[field_name].append(field_name_with_direction)
            if len(field_name_usages[field_name]) > 1:
                duplicated_field_names.add(field_name)

        if duplicated_field_names:
            ambiguous_field_names = ", ".join([
                field_name_with_direction
                for field_name in sorted(duplicated_field_names)
                for field_name_with_direction in field_name_usages[field_name]
            ])
            raise ValueError(
                "Field names can appear at most once for "
                f"{cls.Constants.ordering_field_name}. "
                f"The following was ambiguous: {ambiguous_field_names}."
            )

        return value

    def sort(self, query: Query | Select):
        """Модифицируем сортировку.
        - Добавляем возможность переопределить выражение order_by для поля
          по имени с помощью словаря overwrite_sort_fields.
        - При отсутствии переопределения, в order_by используется поле по имени
          из модели (поведение по умолчанию).
        """
        if not self.ordering_values:
            return query

        for field_name in self.ordering_values:
            direction = Filter.Direction.asc
            if field_name.startswith("-"):
                direction = Filter.Direction.desc
            field_name = field_name.replace("-", "").replace("+", "")

            if field_name in self.Constants.overwrite_sort_fields:
                # Переопределяем order_by_field
                order_by_field = self.Constants.overwrite_sort_fields[field_name]
            else:
                order_by_field = getattr(self.Constants.model, field_name)

            query = query.order_by(getattr(order_by_field, direction)())

        return query

    def filter(self, query: Query | Select):
        """Модифицируем фильтрацию.
        - Добавляем возможность переопределить выражение filter для поля
          по имени с помощью словаря overwrite_filter_fields.
        - При отсутствии переопределения, в filter используется поле по имени
          из модели (поведение по умолчанию).
        """
        for field_name, value in self.filtering_fields:
            if field_name in self.Constants.overwrite_filter_fields:
                query = query.filter(
                    self.Constants.overwrite_filter_fields[field_name](value)
                )
                setattr(self, field_name, None)

        return super().filter(query)

    @classmethod
    def _restrict_sortable_fields(cls, value: list[str]) -> None:
        """Выполняет проверку на допустимость полученных полей сортировки."""

        if cls.Constants.allow_sort_fields is None:
            return

        for field_name in value:
            field_name = field_name.replace("+", "").replace("-", "")
            if field_name not in cls.Constants.allow_sort_fields:
                raise ValueError(
                    "You may only sort by: "
                    f"{', '.join(cls.Constants.allow_sort_fields)}"
                )
