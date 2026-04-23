from dishka import Provider, Scope
from dishka.async_container import make_async_container
from dishka.integrations.fastapi import FastapiProvider

from auth.auth_service import AuthService
from database.uow import IUnitOfWork, UnitOfWork
from event.event_service import EventService
from file.file_service import FileService
from user.user_service import UserService
from venue.venue_service import VenueService

service_provider = Provider(scope=Scope.REQUEST)
service_provider.provide(UnitOfWork, provides=IUnitOfWork)
service_provider.provide(AuthService)
service_provider.provide(UserService)
service_provider.provide(EventService)
service_provider.provide(VenueService)
service_provider.provide(FileService)

di_container = make_async_container(service_provider, FastapiProvider())
