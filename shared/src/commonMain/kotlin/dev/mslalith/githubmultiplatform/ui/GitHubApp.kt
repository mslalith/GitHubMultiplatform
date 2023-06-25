package dev.mslalith.githubmultiplatform.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import dev.mslalith.githubmultiplatform.ui.login.LoginScreen
import dev.mslalith.githubmultiplatform.ui.theme.GitHubMultiplatformTheme

@Composable
fun GitHubApp() {
    GitHubMultiplatformTheme {
        Navigator(
            screen = LoginScreen()
        )
    }
}
