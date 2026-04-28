class AppException(Exception):
    def __init__(self, *args: object) -> None:
        self.message = (
            args[0] if len(args) > 0 else "Произошла ошибка в работе приложения"
        )
        super().__init__(*args)


class DbError(AppException): ...


class DbConnectionError(AppException): ...


class DbIncorrectData(AppException): ...


class UserNotExist(AppException): ...


class UserAlreadyExist(AppException): ...


class InvalidCredentials(AppException): ...


class InvalidToken(AppException): ...


class TokenExpire(AppException): ...


class NotFound(AppException): ...
