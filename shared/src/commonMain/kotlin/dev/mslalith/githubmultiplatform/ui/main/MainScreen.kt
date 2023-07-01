package dev.mslalith.githubmultiplatform.ui.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Gear24
import compose.icons.octicons.PlusCircle24
import compose.icons.octicons.Search24
import compose.icons.octicons.ShareAndroid24
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.common.RoundIcon
import dev.mslalith.githubmultiplatform.ui.common.ScreenTitle
import dev.mslalith.githubmultiplatform.ui.screens.main.explore.ExploreTab
import dev.mslalith.githubmultiplatform.ui.screens.main.home.HomeTab
import dev.mslalith.githubmultiplatform.ui.screens.main.notifications.NotificationsTab
import dev.mslalith.githubmultiplatform.ui.screens.main.profile.ProfileTab
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Blue

class MainScreen : Screen {

    override val key: ScreenKey = toString()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(tab = HomeTab) {
            Scaffold(
                topBar = { TabAwareAppBar() },
                content = {
                    Box(
                        modifier = Modifier.padding(paddingValues = it)
                    ) { CurrentTab() }
                },
                bottomBar = {
                    NavigationBar {
                        TabNavigationItem(tab = HomeTab)
                        TabNavigationItem(tab = NotificationsTab)
                        TabNavigationItem(tab = ExploreTab)
                        TabNavigationItem(tab = ProfileTab)
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val icon = tab.options.icon ?: return
    val label = tab.options.title
    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                painter = icon,
                contentDescription = label
            )
        },
        label = {
            Text(text = label)
        },
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = Bg_Blue,
            selectedIconColor = Bg_Blue
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TabAwareAppBar() {
    TopAppBar(
        title = { AnimatedTitle() },
        navigationIcon = { AnimatedNavIcon() },
        actions = { AnimatedActions() }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedTitle() {
    val tabNavigator = LocalTabNavigator.current
    val screenTitle = (tabNavigator.current as? ScreenTitle)?.titleResource ?: SharedRes.strings._empty
    AnimatedContent(
        targetState = screenTitle,
        transitionSpec = { expandHorizontally() + fadeIn() with shrinkHorizontally() + fadeOut() }
    ) {
        Text(
            text = stringResource(resource = it),
            fontWeight = FontWeight.SemiBold
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedNavIcon() {
    val tabNavigator = LocalTabNavigator.current
    AnimatedContent(
        targetState = tabNavigator.current is ProfileTab,
        transitionSpec = {
            slideIn {
                IntOffset(-it.width, 0)
            } + fadeIn() with slideOut {
                IntOffset(-it.width, -it.height)
            } + fadeOut()
        }
    ) {
        when (it) {
            false -> Unit
            true -> RoundIcon(
                icon = Octicons.ArrowLeft24,
                contentColor = MaterialTheme.colorScheme.onBackground,
                onClick = {}
            )
        }
    }
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
