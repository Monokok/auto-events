import asyncio
import uuid
from pathlib import Path

from fastapi import UploadFile
from pydantic import HttpUrl

from config import settings
from utils.exceptions import AppException


class FileService:
    async def upload_static_file(self, file: UploadFile) -> HttpUrl:
        """Функция записывает полученный файл в static директорию.
        Имя файла заменяется на uuid (расширение остаётся исходным).
        """
        if not file.filename:
            raise AppException("Не получено имя файла")

        file_extension = Path(file.filename).suffix

        # Генерируем уникальное имя с сохранением расширения
        new_filename = f"{uuid.uuid4()}{file_extension}"

        # Полный путь для сохранения
        file_path = settings.STATIC_DIR_PATH / new_filename

        content = await file.read()

        # Выполняем запись файла
        await asyncio.to_thread(self._write_file_sync, file_path, content)

        return HttpUrl(f"{settings.STATIC_URI}/{new_filename}")

    def _write_file_sync(self, file_path: Path, data: bytes) -> None:
        """Синхронная функция записи данных в файл."""
        with open(file_path, "wb") as f:
            f.write(data)
