package dev.mslalith.githubmultiplatform.ui.screens.main.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.Octicons
import compose.icons.octicons.Bell24
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.main.TabTitle

internal object NotificationsTab : Tab, TabTitle {

    override val tabTitle: StringResource = SharedRes.strings.notifications

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(resource = SharedRes.strings.notifications)
            val icon = rememberVectorPainter(image = Octicons.Bell24)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Notifications")
        }
    }
}
