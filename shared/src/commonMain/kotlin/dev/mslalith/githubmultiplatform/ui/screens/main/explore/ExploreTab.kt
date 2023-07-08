package dev.mslalith.githubmultiplatform.ui.screens.main.explore

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.Octicons
import compose.icons.octicons.Telescope24
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.common.TabSection
import dev.mslalith.githubmultiplatform.ui.common.navigator.LocalAppNavigator
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenTitle
import dev.mslalith.githubmultiplatform.ui.common.sectionitem.SectionItemType
import dev.mslalith.githubmultiplatform.ui.common.sectionitem.SectionListItem
import dev.mslalith.githubmultiplatform.ui.screens.awesomelist.AwesomeListScreen

internal object ExploreTab : Tab, ScreenTitle {

    override val titleResource: StringResource = SharedRes.strings.explore

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(resource = SharedRes.strings.explore)
            val icon = rememberVectorPainter(image = Octicons.Telescope24)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val navigator = LocalAppNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Discover(
                onTrendingRepositoriesClick = {},
                onAwesomeListsClick = { navigator.push(item = AwesomeListScreen()) }
            )
        }
    }
}

@Composable
private fun Discover(
    onTrendingRepositoriesClick: () -> Unit,
    onAwesomeListsClick: () -> Unit
) {
    TabSection(
        title = SharedRes.strings.discover,
        content = {
            Column {
                SectionListItem(
                    sectionItemType = SectionItemType.TrendingRepositories,
                    onClick = onTrendingRepositoriesClick
                )
                SectionListItem(
                    sectionItemType = SectionItemType.AwesomeLists,
                    onClick = onAwesomeListsClick
                )
            }
        }
    )
}
