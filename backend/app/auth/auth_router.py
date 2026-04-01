from logging import getLogger
from typing import Annotated, Final

from dishka import FromDishka
from dishka.integrations.fastapi import DishkaRoute, inject
from fastapi import (
    APIRouter,
    Depends,
    HTTPException,
    Security,
    status,
)
from fastapi.security import HTTPAuthorizationCredentials, HTTPBearer

from auth.auth_schemas import AccessTokenDTO
from auth.auth_service import AuthService
from user.user_schemas import UserCreateDTO, UserDTO, UserLoginDTO
from utils.exceptions import (
    InvalidCredentials,
    InvalidToken,
    TokenExpire,
    UserAlreadyExist,
    UserNotExist,
)

logger = getLogger(__name__)
auth_router = APIRouter(prefix="/auth", tags=["Authorization"], route_class=DishkaRoute)

BEARER_HEADER: Final = HTTPBearer(
    scheme_name="AuthHeader",
    description="Name: Authorization JWT\n\nIn: Authorization: 'Bearer' header\n\n",
    auto_error=False,
)


@auth_router.post("/register", response_model=UserDTO)
async def register(new_user: UserCreateDTO, auth_service: FromDishka[AuthService]):
    try:
        result_user = await auth_service.register(new_user)
        logger.info(
            "User (id=%s,username=%s) has successfully registered",
            result_user.id,
            result_user.username,
        )
        return result_user
    except UserAlreadyExist as e:
        logger.info(
            "Registration rejected: User with the same cridentials "
            "(username=%s,email=%s) already exist",
            new_user.username,
            new_user.email,
        )
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT, detail="User already exist"
        ) from e


# эндпоинт входа в аккаунт
@auth_router.post("/login", response_model=AccessTokenDTO)
async def login(user: UserLoginDTO, auth_service: FromDishka[AuthService]):
    try:
        token = await auth_service.login(user)
        logger.info("User (username=%s) has successfully logged in", user.username)

        return AccessTokenDTO(access_token=token)

    except (UserNotExist, InvalidCredentials) as e:
        logger.info(
            "Login operation rejected: Invalid cridentials (username=%s)", user.username
        )
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED, detail="Invalid credentials"
        ) from e


# метод авторизации эндпоинта http, используется при помощи Depends
@inject
async def authorize_http_endpoint(
    auth_header: Annotated[
        HTTPAuthorizationCredentials | None, Security(BEARER_HEADER)
    ],
    auth_service: FromDishka[AuthService],
) -> UserDTO:
    if auth_header is None:
        logger.info("HTTP authorization rejected: header not found")
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED, detail="Unauthorized"
        )
    try:
        user = await auth_service.authorize(auth_header.credentials)
        logger.debug(
            "HTTP authorization success: User (username=%s) is verified", user.username
        )
        return user

    except (InvalidToken, TokenExpire) as e:
        logger.warning("HTTP authorization rejected: Token is invalid or expired")
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Token is invalid or expired",
        ) from e

    except UserNotExist as e:
        logger.warning("HTTP authorization rejected: Token user not exist")
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED, detail="Token user not exist"
        ) from e


async def authorize_http_admin(
    user: Annotated[UserDTO, Depends(authorize_http_endpoint)],
):
    if user.role != "admin":
        logger.info(
            "Authorization rejected: User (username=%s) is not administrator",
            user.username,
        )
        raise HTTPException(
            status_code=status.HTTP_403_FORBIDDEN, detail="You are not an administrator"
        )
    return user


CurrentUser = Annotated[UserDTO, Depends(authorize_http_endpoint)]
CurrentAdmin = Annotated[UserDTO, Depends(authorize_http_admin)]
