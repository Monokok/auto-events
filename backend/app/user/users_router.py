from logging import getLogger

from fastapi import APIRouter, Depends

from auth.auth_router import CurrentAdmin, CurrentUser
from user.user_schemas import UserDTO
from user.user_service import UserService
from utils.dependencies import UOW, Paginator

logger = getLogger(__name__)

user_router = APIRouter(prefix="/users", tags=["Users"])  # создание роутера


# эндпоинт получения объекта текущего пользователя
@user_router.get("/me", response_model=UserDTO)
async def get_current_user(current_user: CurrentUser):
    return current_user


# эндпоинт получения списка всех пользователей
@user_router.get("/all", response_model=list[UserDTO])
async def get_all_users(user: CurrentAdmin, uow: UOW, paginator: Paginator = Depends()):
    return await UserService.get_all_users(paginator.limit, paginator.offset, uow)
