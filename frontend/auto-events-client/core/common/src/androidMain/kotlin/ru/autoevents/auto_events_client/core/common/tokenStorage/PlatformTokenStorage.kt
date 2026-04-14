package ru.autoevents.auto_events_client.core.common.tokenStorage

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class PlatformTokenStorage(
    context: Context
) : TokenStorage {

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override suspend fun saveAccessToken(token: String) {
        prefs.edit { putString("access", token) }
    }

    override suspend fun getAccessToken(): String? =
        prefs.getString("access", null)

    override suspend fun clear() {
        prefs.edit { clear() }
    }
}