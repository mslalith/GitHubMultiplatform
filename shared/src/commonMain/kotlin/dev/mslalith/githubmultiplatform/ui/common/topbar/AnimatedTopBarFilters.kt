package dev.mslalith.githubmultiplatform.ui.common.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.navigator.LocalAppNavigator
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenFilters
import dev.mslalith.githubmultiplatform.ui.common.screen.filters

@Composable
internal fun AnimatedScreenFilters() {
    val navigator = LocalAppNavigator.currentOrThrow
    AnimatedFilters(screenFilters = navigator.lastItem.filters())
}

@Composable
internal fun AnimatedTabFilters() {
    val tabNavigator = LocalTabNavigator.current
    AnimatedFilters(screenFilters = tabNavigator.current.filters())
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedFilters(
    screenFilters: ScreenFilters
) {
    AnimatedContent(
        targetState = screenFilters,
        transitionSpec = { slideInVertically() + fadeIn() with slideOutVertically() + fadeOut() }
    ) {
        Row {
            HorizontalSpace(space = 24.dp)
            with(it) { Filters() }
            HorizontalSpace(space = 24.dp)
        }
    }
}
