package dev.mslalith.githubmultiplatform.ui.screens.starredrepositorylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.data.model.StarredRepository
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.StarsAndLanguage
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenTitle
import dev.mslalith.githubmultiplatform.ui.common.topbar.ScreenAwareTopBar
import dev.mslalith.githubmultiplatform.ui.screens.starredrepositorylist.StarredRepositoryListScreenState.Failed
import dev.mslalith.githubmultiplatform.ui.screens.starredrepositorylist.StarredRepositoryListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.starredrepositorylist.StarredRepositoryListScreenState.Success
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_400
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlin.jvm.Transient

class StarredRepositoryListScreen : Screen, ScreenTitle {

    @Transient
    override val titleResource: StringResource = SharedRes.strings.starred_repositories

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { StarredRepositoryListScreenModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) { screenModel.fetchStarredRepositories() }

        Scaffold(
            topBar = { ScreenAwareTopBar() }
        ) {
            Box(
                modifier = Modifier.padding(paddingValues = it)
            ) {
                when (state) {
                    Failed -> Text(text = "Failed")
                    Loading -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) { CircularProgressIndicator() }

                    is Success -> {
                        Column {
                            val repositories = (state as Success).repositories
                            StarredRepositoriesList(repositories = repositories)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StarredRepositoriesList(
    repositories: List<StarredRepository>
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            items = repositories,
            key = { it.id },
        ) {
            RepositoryItem(
                repository = it,
                onClick = {}
            )
        }
    }
}

@Composable
private fun RepositoryItem(
    repository: StarredRepository,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalSpace(space = 24.dp)
            KamelImage(
                resource = asyncPainterResource(data = repository.ownerAvatarUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(size = 36.dp)
                    .clip(shape = CircleShape)
            )
            HorizontalSpace(space = 12.dp)
            Text(
                text = repository.ownerName,
                color = Bg_Gray_Dark_400,
                fontSize = 14.sp
            )
        }

        Text(
            text = repository.name,
            fontWeight = FontWeight.SemiBold
        )

        if (repository.description != null) {
            Text(
                text = repository.description,
                color = Bg_Gray_Dark_400,
                fontSize = 14.sp
            )
        }

        StarsAndLanguage(
            stars = repository.stars,
            language = repository.language
        )
    }
}
