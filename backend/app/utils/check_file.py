from fastapi import File, HTTPException, UploadFile, status

from config import settings

ALLOWED_PICTURE_MIME_TYPES = {
    "jpeg": "image/jpeg",
    "jpg": "image/jpg",
    "png": "image/png",
    "gif": "image/gif",
}


def valid_mime_type(resume_file: UploadFile, valid_types: set[str]):
    return resume_file.content_type in valid_types


def valid_extension(filename: str, valid_extensions: set[str]) -> bool:
    extesion = filename[filename.rfind(".") + 1 :]

    return any(extesion == member for member in valid_extensions)


async def check_real_file_size(resume_file: UploadFile) -> bool:
    real_file_size = resume_file.file.seek(0, 2)
    await resume_file.seek(0)

    return real_file_size <= settings.MAX_UPLOAD_FILE_SIZE


async def validate_file(
    file: UploadFile, allowed_extensions: set[str], allowed_mime_types: set[str]
):
    """Провести валидацию загружаемого файла.
    Выполняет проверку расширения, MIME типа и размер файла.
    """
    if not valid_extension(str(file.filename), allowed_extensions):
        raise HTTPException(
            status.HTTP_415_UNSUPPORTED_MEDIA_TYPE,
            detail=f"Unsupported media type({allowed_extensions})",
        )
    if not valid_mime_type(file, allowed_mime_types):
        raise HTTPException(
            status.HTTP_415_UNSUPPORTED_MEDIA_TYPE,
            detail=f"Unsupported media type({allowed_extensions})",
        )
    if not await check_real_file_size(file):
        raise HTTPException(
            status.HTTP_413_REQUEST_ENTITY_TOO_LARGE,
            detail="File too large(max 5 Mb)",
        )


async def validate_upload_picture(
    file: UploadFile = File(
        description=f"Разрешённые типы контента: {list(ALLOWED_PICTURE_MIME_TYPES.values())}"
    ),
) -> UploadFile:
    """Провести валидацию загружаемого изображения. Используется в Depends()."""

    await validate_file(
        file, set(ALLOWED_PICTURE_MIME_TYPES), set(ALLOWED_PICTURE_MIME_TYPES.values())
    )
    return file
