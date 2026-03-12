from pydantic import BaseModel, EmailStr, Field

from models import Role


class UserDTO(BaseModel):
    id: int
    username: str = Field(max_length=20)
    email: EmailStr
    role: Role


class UserReadShortDTO(BaseModel):
    id: int
    username: str


class UserCreateDTO(BaseModel):
    username: str = Field(max_length=20, min_length=4)
    email: EmailStr
    password: str = Field(max_length=20, min_length=4)


class UserLoginDTO(BaseModel):
    username: str
    password: str
