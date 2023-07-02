package dev.mslalith.githubmultiplatform.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import dev.mslalith.githubmultiplatform.ui.common.navigator.ProvideAppNavigator
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreen
import dev.mslalith.githubmultiplatform.ui.theme.GitHubMultiplatformTheme

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun GitHubApp() {
    GitHubMultiplatformTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BottomSheetNavigator(
                sheetBackgroundColor = MaterialTheme.colorScheme.surface,
                sheetContentColor = MaterialTheme.colorScheme.onSurface
            ) {
                Navigator(
                    screen = LoginScreen,
                    content = { navigator ->
                        ProvideAppNavigator(
                            navigator = navigator,
                            content = { SlideTransition(navigator = navigator) }
                        )
                    }
                )
            }
        }
    }
}
