from database.uow import IUnitOfWork
from user.user_schemas import UserDTO


class UserService:
    """
    Сервис выполняет бизнес-логику по работе с пользователями
    (помимо авторизации/аутенфикации/обновления)
    """

    uow: IUnitOfWork

    def __init__(self, uow: IUnitOfWork) -> None:
        self.uow = uow

    # метод получения списка всех пользователей
    async def get_all_users(self, limit: int, offset: int) -> list[UserDTO]:
        async with self.uow:
            users = await self.uow.users.get_all(limit, offset)
            return [UserDTO.model_validate(x, from_attributes=True) for x in users]
