import time

import jwt
from passlib.context import CryptContext

from config import settings
from database.uow import IUnitOfWork
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

    uow: IUnitOfWork

    def __init__(self, uow: IUnitOfWork) -> None:
        self.uow = uow

    # метод регистрации пользователя

    async def register(
        self,
        user: UserCreateDTO,
    ) -> UserDTO:
        async with self.uow:
            check = await self.uow.users.get_by_username_or_email(
                user.username, user.email
            )
            if check is not None:
                raise UserAlreadyExist()
            hashed_password = AuthService.crypto_context.hash(user.password)
            new_user = User(
                username=user.username, email=user.email, password=hashed_password
            )
            new_user = await self.uow.users.create_user(new_user)
            return UserDTO.model_validate(new_user, from_attributes=True)

    # метод аутентификации пользователя
    async def login(self, user: UserLoginDTO) -> str:
        async with self.uow:
            db_user = await self.uow.users.get_by_username(user.username)
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
    async def authorize(self, token: str):
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
        async with self.uow:
            user = await self.uow.users.get_by_id(user_id)
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
