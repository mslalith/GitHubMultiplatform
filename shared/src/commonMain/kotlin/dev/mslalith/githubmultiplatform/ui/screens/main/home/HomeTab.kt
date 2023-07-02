package dev.mslalith.githubmultiplatform.ui.screens.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.Octicons
import compose.icons.octicons.Home24
import compose.icons.octicons.PlusCircle24
import compose.icons.octicons.Search24
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.common.RoundIcon
import dev.mslalith.githubmultiplatform.ui.common.TabSection
import dev.mslalith.githubmultiplatform.ui.common.navigator.LocalAppNavigator
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenActions
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenTitle
import dev.mslalith.githubmultiplatform.ui.common.sectionitem.SectionItemType
import dev.mslalith.githubmultiplatform.ui.common.sectionitem.SectionListItem
import dev.mslalith.githubmultiplatform.ui.screens.repositorylist.RepositoryListScreen
import dev.mslalith.githubmultiplatform.ui.screens.starredrepositorylist.StarredRepositoryListScreen

internal object HomeTab : Tab, ScreenTitle, ScreenActions {

    override val titleResource: StringResource = SharedRes.strings.home

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
    override fun RowScope.Actions() {
        RoundIcon(
            icon = Octicons.Search24,
            onClick = {}
        )
        RoundIcon(
            icon = Octicons.PlusCircle24,
            onClick = {}
        )
    }

    @Composable
    override fun Content() {
        val navigator = LocalAppNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            MyWork(
                onIssuesClick = {},
                onPullRequestsClick = {},
                onDiscussionsClick = {},
                onRepositoriesClick = { navigator.push(item = RepositoryListScreen()) },
                onStarredRepositoriesClick = { navigator.push(item = StarredRepositoryListScreen()) }
            )
        }
    }
}

@Composable
private fun MyWork(
    onIssuesClick: () -> Unit,
    onPullRequestsClick: () -> Unit,
    onDiscussionsClick: () -> Unit,
    onRepositoriesClick: () -> Unit,
    onStarredRepositoriesClick: () -> Unit,
) {
    TabSection(
        title = SharedRes.strings.my_work,
        content = {
            Column {
                SectionListItem(
                    sectionItemType = SectionItemType.Issues,
                    onClick = onIssuesClick
                )
                SectionListItem(
                    sectionItemType = SectionItemType.PullRequests,
                    onClick = onPullRequestsClick
                )
                SectionListItem(
                    sectionItemType = SectionItemType.Discussions,
                    onClick = onDiscussionsClick
                )
                SectionListItem(
                    sectionItemType = SectionItemType.Repositories,
                    onClick = onRepositoriesClick
                )
                SectionListItem(
                    sectionItemType = SectionItemType.StarredRepositories,
                    onClick = onStarredRepositoriesClick
                )
            }
        }
    )
}
