package dev.mslalith.githubmultiplatform.ui.main.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.Octicons
import compose.icons.octicons.Person24
import compose.icons.octicons.Repo16
import compose.icons.octicons.Star16
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.common.HorizontalLine
import dev.mslalith.githubmultiplatform.ui.common.VerticalSpace
import dev.mslalith.githubmultiplatform.ui.main.profile.ProfileTabState.Failed
import dev.mslalith.githubmultiplatform.ui.main.profile.ProfileTabState.Loading
import dev.mslalith.githubmultiplatform.ui.main.profile.ProfileTabState.Success
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_400
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_900
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Yellow

internal object ProfileTab : Tab {

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
    override fun Content() {
        val screenModel = rememberScreenModel { ProfileTabModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) { screenModel.fetchProfile() }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when (state) {
                Failed -> Text(text = "Failed")
                Loading -> CircularProgressIndicator()
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

                        HorizontalLine(height = 16.dp, color = Color.Black)
                        PinnedRepositoriesSection(pinnedRepositories = profile.pinnedRepositories)

                        VerticalSpace(space = 16.dp)
                        HorizontalLine(height = 1.dp, color = Bg_Gray_Dark_900)

                        SectionItem(
                            name = stringResource(resource = SharedRes.strings.repositories),
                            icon = Octicons.Repo16,
                            iconBackgroundColor = Bg_Gray_Dark_400,
                            count = profile.repositoriesCount,
                            onClick = {}
                        )

                        SectionItem(
                            name = stringResource(resource = SharedRes.strings.starred),
                            icon = Octicons.Star16,
                            iconBackgroundColor = Bg_Yellow,
                            count = profile.starredRepositoriesCount,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}
