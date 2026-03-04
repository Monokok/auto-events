import time

import jwt
from passlib.context import CryptContext

from config import settings
from database.uow import UnitOfWork
from models import User
from user.user_schemas import UserCreateDTO, UserDTO, UserLoginDTO
from utils.exceptions import (
    InvalidCredentials,
    InvalidToken,
    TokenExpire,
    UserAlreadyExist,
    UserNotExist,
)


class AuthService:
    """
    Сервис выполняет бизнес-логику аутенфикации и авторизации
    """

    crypto_context = CryptContext(schemes=["bcrypt"])

    # метод регистрации пользователя
    @staticmethod
    async def register(
        user: UserCreateDTO,
        uow: UnitOfWork,
    ) -> UserDTO:
        async with uow:
            check = await uow.users.get_by_username_or_email(user.username, user.email)
            if check is not None:
                raise UserAlreadyExist()
            hashed_password = AuthService.crypto_context.hash(user.password)
            new_user = User(
                username=user.username, email=user.email, password=hashed_password
            )
            new_user = await uow.users.create_user(new_user)
            return UserDTO.model_validate(new_user, from_attributes=True)

    # метод аутентификации пользователя
    @staticmethod
    async def login(user: UserLoginDTO, uow: UnitOfWork) -> str:
        async with uow:
            db_user = await uow.users.get_by_username(user.username)
            if db_user is None:
                raise UserNotExist
            pass_verify = AuthService.crypto_context.verify(
                user.password, db_user.password
            )
            if not pass_verify:
                raise InvalidCredentials
            token = AuthService._create_jwt_token(db_user.id)
            return token

    # метод авторизации пользователя
    @staticmethod
    async def authorize(token: str, uow: UnitOfWork):
        decoded_token = AuthService._verify_jwt_token(token)
        if (
            decoded_token is None
            or "sub" not in decoded_token
            or "exp" not in decoded_token
        ):
            raise InvalidToken

        if decoded_token["exp"] - int(time.time()) < 0:
            raise TokenExpire
        user_id = int(decoded_token["sub"])
        async with uow:
            user = await uow.users.get_by_id(user_id)
            if user is None:
                raise UserNotExist
            return UserDTO.model_validate(user, from_attributes=True)

    # метод создания jwt токена
    @staticmethod
    def _create_jwt_token(user_id: int):
        expiration_date = int(time.time()) + settings.JWT_EXPIRATION_TIME
        token_payload = {"sub": str(user_id), "exp": expiration_date}
        token = jwt.encode(token_payload, settings.JWT_SECRET, algorithm="HS256")
        return token

    # метод проверки jwt токена
    @staticmethod
    def _verify_jwt_token(token: str):
        try:
            decoded_data = jwt.decode(token, settings.JWT_SECRET, algorithms=["HS256"])
            return decoded_data
        except jwt.PyJWTError:
            return None
