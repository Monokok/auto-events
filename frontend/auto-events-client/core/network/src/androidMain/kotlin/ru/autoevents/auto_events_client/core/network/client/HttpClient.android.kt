package ru.autoevents.auto_events_client.core.network.client

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

private val trustingManager = object : X509TrustManager {
    override fun checkClientTrusted(chain: Array<X509Certificate>?, authType: String?) {}
    override fun checkServerTrusted(chain: Array<X509Certificate>?, authType: String?) {}
    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}

private val sslContext = SSLContext.getInstance("TLS").apply {
    init(null, arrayOf(trustingManager), SecureRandom())
}


private fun HttpClientConfig<OkHttpConfig>.sslInit() {
    engine {
        config {
            sslSocketFactory(sslContext.socketFactory, trustingManager)
        }
    }
}

actual fun createHttpClient(): HttpClient =
    HttpClient(OkHttp) {
        sslInit()
        init()
    }