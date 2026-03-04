from contextlib import asynccontextmanager

import uvicorn
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from auth.auth_router import auth_router
from config import EnvEnum, settings
from database.database import DatabasePgs
from user.users_router import user_router
from utils.logging import LOGGING_CONFIG


@asynccontextmanager
async def app_lifespan(app: FastAPI):
    await DatabasePgs.init_db()  # инициализация БД при запуске
    yield


app = FastAPI(
    title=settings.PROJECT_NAME,
    debug=settings.DEBUG,
    lifespan=app_lifespan,
    root_path="/api",
)

if settings.CORS_ALLOWED_ORIGINS:
    app.add_middleware(
        CORSMiddleware,
        allow_origins=[
            # Убераем слеш в конце пути, если есть
            str(origin).rstrip("/")
            for origin in settings.CORS_ALLOWED_ORIGINS
        ],
        allow_credentials=True,
        allow_methods=["*"],
        allow_headers=["*"],
    )


app.include_router(auth_router)
app.include_router(user_router)



if __name__ == "__main__":
    # запуск веб-сервера uvicorn
    uvicorn.run(
        app="main:app",
        reload=settings.ENV_MODE is EnvEnum.dev,
        host="0.0.0.0",
        log_config=LOGGING_CONFIG,
    )
