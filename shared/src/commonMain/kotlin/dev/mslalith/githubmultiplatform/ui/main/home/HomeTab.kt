package dev.mslalith.githubmultiplatform.ui.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.Octicons
import compose.icons.octicons.Home24
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes

internal object HomeTab : Tab {

    override val key: ScreenKey = toString()

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(resource = SharedRes.strings.home)
            val icon = rememberVectorPainter(image = Octicons.Home24)

            return remember {
                TabOptions(
                    index = 0u,
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
            Text(text = "Home")
        }
    }
}
