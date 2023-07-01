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
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import dev.mslalith.githubmultiplatform.ui.common.RoundIcon
import dev.mslalith.githubmultiplatform.ui.common.navigator.LocalAppNavigator

@Composable
internal fun AnimatedScreenNavIcon() {
    val navigator = LocalAppNavigator.currentOrThrow
    AnimatedNavIcon(
        showNav = navigator.canPop,
        onClick = navigator::pop
    )
}

@Composable
internal fun AnimatedTabNavIcon(
    showNav: (Tab) -> Boolean
) {
    val navigator = LocalAppNavigator.currentOrThrow
    val tabNavigator = LocalTabNavigator.current
    AnimatedNavIcon(
        showNav = showNav(tabNavigator.current),
        onClick = navigator::pop
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedNavIcon(
    showNav: Boolean,
    onClick: () -> Unit
) {
    AnimatedContent(
        targetState = showNav,
        transitionSpec = {
            slideIn {
                IntOffset(-it.width, 0)
            } + fadeIn() with slideOut {
                IntOffset(-it.width, -it.height)
            } + fadeOut()
        }
    ) { show ->
        when (show) {
            false -> Unit
            true -> RoundIcon(
                icon = Octicons.ArrowLeft24,
                contentColor = MaterialTheme.colorScheme.onBackground,
                onClick = onClick
            )
        }
    }
}
