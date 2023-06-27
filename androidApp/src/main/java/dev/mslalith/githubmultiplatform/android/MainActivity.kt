package dev.mslalith.githubmultiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.mslalith.githubmultiplatform.deeplink.DeepLinkRegistry
import dev.mslalith.githubmultiplatform.ui.GitHubApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { GitHubApp() }
    }

    override fun onResume() {
        super.onResume()
        intent.data?.toString()?.let { DeepLinkRegistry.handle(deepLink = it) }
    }
}
