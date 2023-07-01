package dev.mslalith.githubmultiplatform.ui.common.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.with
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.RoundIcon
import dev.mslalith.githubmultiplatform.ui.common.navigator.LocalAppNavigator

@Composable
internal fun AnimatedScreenNavIcon() {
    val navigator = LocalAppNavigator.currentOrThrow
    AnimatedNavIcon(
        showNavIcon = navigator.canPop,
        onClick = navigator::pop
    )
}

@Composable
internal fun AnimatedTabNavIcon(
    showNavIcon: (Tab) -> Boolean
) {
    val navigator = LocalAppNavigator.currentOrThrow
    val tabNavigator = LocalTabNavigator.current
    AnimatedNavIcon(
        showNavIcon = showNavIcon(tabNavigator.current),
        onClick = navigator::pop
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedNavIcon(
    showNavIcon: Boolean,
    onClick: () -> Unit
) {
    AnimatedContent(
        targetState = showNavIcon,
        transitionSpec = {
            slideIn {
                IntOffset(-it.width, 0)
            } + fadeIn() with slideOut {
                IntOffset(-it.width, -it.height)
            } + fadeOut()
        }
    ) { show ->
        when (show) {
            false -> HorizontalSpace(space = 12.dp)
            true -> RoundIcon(
                icon = Octicons.ArrowLeft24,
                contentColor = MaterialTheme.colorScheme.onBackground,
                onClick = onClick
            )
        }
    }
}
