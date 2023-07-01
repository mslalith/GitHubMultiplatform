package dev.mslalith.githubmultiplatform.ui.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.Octicons
import compose.icons.octicons.Home24
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.common.TabSection
import dev.mslalith.githubmultiplatform.ui.common.listitem.SectionItemType
import dev.mslalith.githubmultiplatform.ui.common.listitem.SectionListItem
import dev.mslalith.githubmultiplatform.ui.main.TabTitle
import dev.mslalith.githubmultiplatform.ui.main.home.HomeTabState.Failed
import dev.mslalith.githubmultiplatform.ui.main.home.HomeTabState.Loading
import dev.mslalith.githubmultiplatform.ui.main.home.HomeTabState.Success

internal object HomeTab : Tab, TabTitle {

    override val tabTitle: StringResource = SharedRes.strings.home

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
        val screenModel = rememberScreenModel { HomeTabModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) { screenModel.fetchRepositories() }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when (state) {
                Failed -> Text(text = "Failed")
                Loading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
                is Success -> {
                    Column {
                        MyWork()
                    }
                }
            }
        }
    }
}

@Composable
private fun MyWork() {
    TabSection(
        title = SharedRes.strings.my_work,
        content = {
            Column {
                SectionListItem(
                    sectionItemType = SectionItemType.Issues,
                    onClick = {}
                )
                SectionListItem(
                    sectionItemType = SectionItemType.PullRequests,
                    onClick = {}
                )
                SectionListItem(
                    sectionItemType = SectionItemType.Discussions,
                    onClick = {}
                )
                SectionListItem(
                    sectionItemType = SectionItemType.Repositories,
                    onClick = {}
                )
                SectionListItem(
                    sectionItemType = SectionItemType.StarredRepositories,
                    onClick = {}
                )
            }
        }
    )
}
