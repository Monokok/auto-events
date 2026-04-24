package ru.autoevents.auto_events_client.core.common.tokenStorage

import kotlinx.browser.window

class PlatformTokenStorage : TokenStorage {

    private val storage = window.localStorage
    private val KEY = "access_token"

    override suspend fun saveAccessToken(token: String) {
        storage.setItem(KEY, token)
    }

    override suspend fun getAccessToken(): String? =
        storage.getItem(KEY)

    override suspend fun clear() {
        storage.removeItem(KEY)
    }
}