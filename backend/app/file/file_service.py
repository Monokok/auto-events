from fastapi import UploadFile
from pydantic import HttpUrl


class FileService:
    async def upload_static_file(self, file: UploadFile) -> HttpUrl:
        pass
