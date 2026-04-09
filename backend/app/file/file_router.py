from typing import Annotated

from dishka import FromDishka
from dishka.integrations.fastapi import DishkaRoute
from fastapi import APIRouter, Depends, UploadFile
from pydantic import HttpUrl

from file.file_service import FileService
from utils.check_file import validate_upload_picture

file_router = APIRouter(prefix="/files", tags=["File"], route_class=DishkaRoute)


@file_router.post("", response_model=HttpUrl, summary="Upload static file")
async def upload_static_file(
    file: Annotated[UploadFile, Depends(validate_upload_picture)],
    file_service: FromDishka[FileService],
):

    return await file_service.upload_static_file(file)
