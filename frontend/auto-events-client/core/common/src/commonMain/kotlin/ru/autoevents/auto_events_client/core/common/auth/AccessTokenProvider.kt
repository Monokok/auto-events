package ru.autoevents.auto_events_client.core.common.auth

interface AccessTokenProvider {
    suspend fun getAccessToken(): String?
}

class EmptyAccessTokenProvider : AccessTokenProvider {
    override suspend fun getAccessToken(): String? = null
}
