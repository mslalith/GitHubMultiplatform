package dev.mslalith.githubmultiplatform.ui.common.topbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace

@Composable
fun ScreenAwareTopBar() {
    TopBar(
        title = { AnimatedScreenTitle() },
        navigationIcon = { AnimatedScreenNavIcon() },
        actions = { AnimatedScreenActions() },
        filters = { AnimatedScreenFilters() }
    )
}

@Composable
fun TabAwareTopBar(
    showNavIcon: (Tab) -> Boolean = { false }
) {
    TopBar(
        title = { AnimatedTabTitle() },
        navigationIcon = { AnimatedTabNavIcon(showNavIcon = showNavIcon) },
        actions = { AnimatedTabActions() },
        filters = { AnimatedTabFilters() }
    )
}

@Composable
private fun TopBar(
    title: @Composable () -> Unit,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable () -> Unit,
    filters: @Composable () -> Unit
) {
    Column {
        Row(
            modifier = Modifier.height(height = 64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalSpace(space = 4.dp)
            navigationIcon()
            HorizontalSpace(space = 4.dp)

            Box(
                modifier = Modifier.weight(weight = 1f)
            ) { title() }

            HorizontalSpace(space = 4.dp)
            actions()
            HorizontalSpace(space = 4.dp)
        }
        filters()
    }
}
