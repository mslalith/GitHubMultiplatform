package dev.mslalith.githubmultiplatform.ui.common.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAwareTopBar() {
    TopAppBar(
        title = { AnimatedScreenTitle() },
        navigationIcon = { AnimatedScreenNavIcon() },
        actions = { AnimatedScreenActions() }
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
        actions = { AnimatedTabActions() }
    )
}
