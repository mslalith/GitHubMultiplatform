package dev.mslalith.githubmultiplatform.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dev.mslalith.githubmultiplatform.ui.login.LoginScreen
import dev.mslalith.githubmultiplatform.ui.theme.GitHubMultiplatformTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GitHubApp() {
    GitHubMultiplatformTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigator(
                screen = LoginScreen,
                content = { SlideTransition(navigator = it) }
            )
        }
    }
}
