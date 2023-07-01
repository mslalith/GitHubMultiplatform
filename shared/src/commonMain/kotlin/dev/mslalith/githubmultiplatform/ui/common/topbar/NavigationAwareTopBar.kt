package dev.mslalith.githubmultiplatform.ui.common.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import compose.icons.Octicons
import compose.icons.octicons.Gear24
import compose.icons.octicons.PlusCircle24
import compose.icons.octicons.Search24
import compose.icons.octicons.ShareAndroid24
import dev.mslalith.githubmultiplatform.ui.common.RoundIcon
import dev.mslalith.githubmultiplatform.ui.screens.main.home.HomeTab
import dev.mslalith.githubmultiplatform.ui.screens.main.profile.ProfileTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAwareTopBar() {
    TopAppBar(
        title = { AnimatedScreenTitle() },
        navigationIcon = { AnimatedScreenNavIcon() },
        actions = { AnimatedActions() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabAwareTopBar(
    showNav: (Tab) -> Boolean
) {
    TopAppBar(
        title = { AnimatedTabTitle() },
        navigationIcon = { AnimatedTabNavIcon(showNav = showNav) },
        actions = { AnimatedActions() }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedActions() {
    val tabNavigator = LocalTabNavigator.current
    AnimatedContent(
        targetState = tabNavigator.current,
        transitionSpec = { slideInHorizontally { it / 2 } + fadeIn() with slideOutHorizontally { it / 2 } + fadeOut() }
    ) {
        when (it) {
            HomeTab -> {
                Row {
                    RoundIcon(
                        icon = Octicons.Search24,
                        onClick = {}
                    )
                    RoundIcon(
                        icon = Octicons.PlusCircle24,
                        onClick = {}
                    )
                }
            }
            ProfileTab -> {
                Row {
                    RoundIcon(
                        icon = Octicons.ShareAndroid24,
                        onClick = {}
                    )
                    RoundIcon(
                        icon = Octicons.Gear24,
                        onClick = {}
                    )
                }
            }
            else -> Unit
        }
    }
}
