package dev.mslalith.githubmultiplatform.deeplink

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun Screen.DeepLinkHandler(block: (String) -> Unit) {
    LaunchedEffect(key1 = key) {
        with(DeepLinkRegistry.buffer) {
            collectLatest {
                block(it)
                resetReplayCache()
            }
        }
    }
}
