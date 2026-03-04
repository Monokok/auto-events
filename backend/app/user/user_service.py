from database.uow import UnitOfWork
from user.user_schemas import UserDTO


class UserService:
    """
    Сервис выполняет бизнес-логику по работе с пользователями
    (помимо авторизации/аутенфикации/обновления)
    """

    # метод получения списка всех пользователей
    @staticmethod
    async def get_all_users(
        limit: int, offset: int, uow: UnitOfWork
    ) -> list[UserDTO]:
        async with uow:
            users = await uow.users.get_all(limit, offset)
            return [UserDTO.model_validate(x, from_attributes=True) for x in users]
