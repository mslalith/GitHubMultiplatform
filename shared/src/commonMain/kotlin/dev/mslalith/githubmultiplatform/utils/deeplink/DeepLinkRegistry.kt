package dev.mslalith.githubmultiplatform.utils.deeplink

import kotlinx.coroutines.flow.MutableSharedFlow

object DeepLinkRegistry {

    internal val buffer = MutableSharedFlow<String>(replay = 1)

    fun handle(deepLink: String) {
        buffer.tryEmit(value = deepLink)
    }
}
