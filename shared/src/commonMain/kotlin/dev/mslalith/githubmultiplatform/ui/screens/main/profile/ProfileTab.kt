package dev.mslalith.githubmultiplatform.ui.screens.main.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.Octicons
import compose.icons.octicons.Gear24
import compose.icons.octicons.Person24
import compose.icons.octicons.ShareAndroid24
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.common.HorizontalLine
import dev.mslalith.githubmultiplatform.ui.common.RoundIcon
import dev.mslalith.githubmultiplatform.ui.common.VerticalSpace
import dev.mslalith.githubmultiplatform.ui.common.sectionitem.SectionItemType
import dev.mslalith.githubmultiplatform.ui.common.sectionitem.SectionListItem
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenTitle
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenActions
import dev.mslalith.githubmultiplatform.ui.screens.main.profile.ProfileTabState.Failed
import dev.mslalith.githubmultiplatform.ui.screens.main.profile.ProfileTabState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.main.profile.ProfileTabState.Success
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_500
import dev.mslalith.githubmultiplatform.ui.theme.borderLight

internal object ProfileTab : Tab, ScreenTitle, ScreenActions {

    override val titleResource: StringResource = SharedRes.strings.profile

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(resource = SharedRes.strings.profile)
            val icon = rememberVectorPainter(image = Octicons.Person24)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun RowScope.Actions() {
        RoundIcon(
            icon = Octicons.ShareAndroid24,
            onClick = {}
        )
        RoundIcon(
            icon = Octicons.Gear24,
            onClick = {}
        )
    }

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { ProfileTabModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) { screenModel.fetchProfile() }

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
                    val profile = (state as Success).profileTabUiState
                    Column(
                        modifier = Modifier.verticalScroll(state = rememberScrollState())
                    ) {
                        UserProfileInfo(
                            name = profile.name,
                            login = profile.login,
                            avatarUrl = profile.avatarUrl,
                            pronouns = profile.pronouns,
                            email = profile.email,
                            followersCount = profile.followersCount,
                            followingCount = profile.followingCount
                        )

                        HorizontalLine(height = 1.dp, color = MaterialTheme.colorScheme.borderLight)
                        PinnedRepositoriesSection(pinnedRepositories = profile.pinnedRepositories)

                        VerticalSpace(space = 16.dp)
                        HorizontalLine(height = 1.dp, color = MaterialTheme.colorScheme.borderLight)

                        SectionListItem(
                            sectionItemType = SectionItemType.Repositories,
                            trailing = {
                                Text(
                                    text = profile.repositoriesCount.toString(),
                                    color = Bg_Gray_Dark_500
                                )
                            },
                            onClick = {}
                        )
                        SectionListItem(
                            sectionItemType = SectionItemType.StarredRepositories,
                            trailing = {
                                Text(
                                    text = profile.starredRepositoriesCount.toString(),
                                    color = Bg_Gray_Dark_500
                                )
                            },
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}
