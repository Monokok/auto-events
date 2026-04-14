package ru.autoevents.auto_events_client.core.common.tokenStorage

interface TokenStorage {
    suspend fun saveAccessToken(token: String)
    suspend fun getAccessToken(): String?
    suspend fun clear()
}