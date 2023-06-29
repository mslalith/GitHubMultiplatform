package dev.mslalith.githubmultiplatform.ui.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.main.profile.ProfileTabState.Failed
import dev.mslalith.githubmultiplatform.ui.main.profile.ProfileTabState.Loading
import dev.mslalith.githubmultiplatform.ui.main.profile.ProfileTabState.Success

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
                    Column {
                        UserProfileInfo(
                            name = profile.name,
                            login = profile.login,
                            avatarUrl = profile.avatarUrl,
                            pronouns = profile.pronouns,
                            email = profile.email,
                            followersCount = profile.followersCount,
                            followingCount = profile.followingCount
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(height = 16.dp)
                                .background(color = Color.Black)
                        )
                    }
                }
            }
        }
    }
}
