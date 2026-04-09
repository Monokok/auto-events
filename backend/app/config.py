from enum import StrEnum
from pathlib import Path

from pydantic import PostgresDsn, computed_field
from pydantic_settings import BaseSettings, SettingsConfigDict


class EnvEnum(StrEnum):
    dev = "dev"
    prod = "prod"


class Settings(BaseSettings):
    model_config = SettingsConfigDict(
        env_file=Path(__file__).parents[1].joinpath(".env"),
        env_file_encoding="utf-8",
        case_sensitive=True,
        extra="ignore",
    )

    # Application
    PROJECT_NAME: str = "AutoEvents"
    ENV_MODE: EnvEnum = EnvEnum.dev
    DEBUG: bool = False
    LOG_LEVEL: str = "INFO"
    JWT_SECRET: str = "secret"
    JWT_EXPIRATION_TIME: int = 3600
    CORS_ALLOWED_ORIGINS: list[str] = []
    SSL_ENABLE: bool = False
    SSL_CERT_FILE: str = "certfile.pem"
    SSL_KEY_FILE: str = "keyfile.pem"
    DOMAIN: str = "localhost"
    MAX_UPLOAD_FILE_SIZE: int = 5242880

    # Database
    DB_USER: str = "autoevents"
    DB_PASSWORD: str = "password"
    DB_NAME: str = "autoevents-db"
    DB_HOST: str = "127.0.0.1"
    DB_PORT: int = 5432

    ADMIN_PASSWORD: str = "password_hash"

    @computed_field
    @property
    def APP_BASE_URI(self) -> str:
        proto = "https" if self.SSL_ENABLE else "http"
        return f"{proto}://{self.DOMAIN}/api"

    @computed_field
    @property
    def STATIC_URI(self) -> str:
        return f"{self.APP_BASE_URI}/static"

    @computed_field
    @property
    def STATIC_DIR_PATH(self) -> Path:
        static_path = Path(__file__).parent / "static"
        static_path.mkdir(exist_ok=True)
        return static_path

    @computed_field
    @property
    def POSTGRES_DB_URI(self) -> PostgresDsn:
        return PostgresDsn.build(
            scheme="postgresql+asyncpg",
            username=self.DB_USER,
            password=self.DB_PASSWORD,
            host=self.DB_HOST,
            port=self.DB_PORT,
            path=self.DB_NAME,
        )

    @computed_field
    @property
    def POSTGRES_DB_HOST_URI(self) -> PostgresDsn:
        return PostgresDsn.build(
            scheme="postgresql+asyncpg",
            username=self.DB_USER,
            password=self.DB_PASSWORD,
            host=self.DB_HOST,
            port=self.DB_PORT,
            path="postgres",
        )


settings = Settings()
