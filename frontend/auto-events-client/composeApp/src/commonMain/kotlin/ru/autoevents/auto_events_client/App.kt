package ru.autoevents.auto_events_client

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import coil3.ImageLoader
import coil3.compose.LocalPlatformContext
import coil3.util.DebugLogger
import io.ktor.client.*
import org.koin.compose.koinInject
import ru.autoevents.auto_events_client.core.ui.models.LocalImageLoader
import ru.autoevents.auto_events_client.core.ui.models.addNetworkFetcher
import ru.autoevents.auto_events_client.feature.home.ui.main.MainScreen

@Composable
fun App() {
    MaterialTheme {
        val platformContext = LocalPlatformContext.current
        val httpClient = koinInject<HttpClient>()
        val imageLoader: ImageLoader = remember {
            ImageLoader.Builder(platformContext)
                .components {
                    addNetworkFetcher(httpClient)
                }
                .logger(DebugLogger())
                .build()
        }

        CompositionLocalProvider(
            LocalImageLoader provides imageLoader
        ) {
            Navigator(MainScreen())
        }
    }
}