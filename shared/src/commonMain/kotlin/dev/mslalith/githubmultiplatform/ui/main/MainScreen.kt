package dev.mslalith.githubmultiplatform.ui.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import dev.mslalith.githubmultiplatform.ui.main.explore.ExploreTab
import dev.mslalith.githubmultiplatform.ui.main.home.HomeTab
import dev.mslalith.githubmultiplatform.ui.main.notifications.NotificationsTab
import dev.mslalith.githubmultiplatform.ui.main.profile.ProfileTab
import dev.mslalith.githubmultiplatform.ui.theme.Dark_Blue

class MainScreen : Screen {

    override val key: ScreenKey = toString()

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(tab = HomeTab) {
            Scaffold(
                content = {
                    CurrentTab()
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
            selectedTextColor = Dark_Blue,
            selectedIconColor = Dark_Blue
        )
    )
}
