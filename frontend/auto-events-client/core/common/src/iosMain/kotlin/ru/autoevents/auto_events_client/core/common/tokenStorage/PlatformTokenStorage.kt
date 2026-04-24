@file:OptIn(ExperimentalForeignApi::class)

package ru.autoevents.auto_events_client.core.common.tokenStorage

import kotlinx.cinterop.*
import platform.CoreFoundation.CFDictionaryRef
import platform.CoreFoundation.CFTypeRefVar
import platform.CoreFoundation.kCFBooleanTrue
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Security.*

class PlatformTokenStorage : TokenStorage {

    private fun save(key: String, value: String) {
        val data = value.encodeToByteArray().toNSData()

        val query = mapOf(
            kSecClass to kSecClassGenericPassword,
            kSecAttrAccount to key,
            kSecValueData to data
        ) as CFDictionaryRef

        SecItemDelete(query)
        SecItemAdd(query, null)
    }

    private fun get(key: String): String? {
        val query = mapOf(
            kSecClass to kSecClassGenericPassword,
            kSecAttrAccount to key,
            kSecReturnData to kCFBooleanTrue!!,
            kSecMatchLimit to kSecMatchLimitOne
        ) as CFDictionaryRef

        memScoped {
            val result = alloc<CFTypeRefVar>()
            val status = SecItemCopyMatching(query, result.ptr)

            if (status == errSecSuccess) {
                val data = result.value as NSData
                return NSString.create(data, NSUTF8StringEncoding) as String
            }
        }
        return null
    }

    override suspend fun saveAccessToken(token: String) {
        save("access", token)
    }

    override suspend fun getAccessToken(): String? = get("access")

    override suspend fun clear() {
        val query = mapOf(
            kSecClass to kSecClassGenericPassword
        ) as CFDictionaryRef

        SecItemDelete(query)
    }
}
@OptIn(BetaInteropApi::class)
fun ByteArray.toNSData(): NSData =
    if (isEmpty()) {
        NSData()
    } else {
        usePinned {
            NSData.create(
                bytes = it.addressOf(0),
                length = size.toULong()
            )
        }
    }