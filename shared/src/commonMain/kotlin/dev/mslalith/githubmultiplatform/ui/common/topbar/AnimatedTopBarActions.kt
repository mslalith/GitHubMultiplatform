package dev.mslalith.githubmultiplatform.ui.common.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import dev.mslalith.githubmultiplatform.ui.common.navigator.LocalAppNavigator
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenActions
import dev.mslalith.githubmultiplatform.ui.common.screen.actions

@Composable
internal fun AnimatedScreenActions() {
    val navigator = LocalAppNavigator.currentOrThrow
    AnimatedActions(topBarActions = navigator.lastItem.actions())
}

@Composable
internal fun AnimatedTabActions() {
    val tabNavigator = LocalTabNavigator.current
    AnimatedActions(topBarActions = tabNavigator.current.actions())
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedActions(
    topBarActions: ScreenActions
) {
    AnimatedContent(
        targetState = topBarActions,
        transitionSpec = { slideInHorizontally { it / 2 } + fadeIn() with slideOutHorizontally { it / 2 } + fadeOut() }
    ) {
        Row {
            with(it) { Actions() }
        }
    }
}
