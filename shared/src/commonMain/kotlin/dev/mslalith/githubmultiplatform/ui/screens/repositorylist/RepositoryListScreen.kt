package dev.mslalith.githubmultiplatform.ui.screens.repositorylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import cafe.adriel.voyager.core.screen.ScreenKey
import dev.mslalith.githubmultiplatform.data.model.Repository
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.VerticalSpace
import dev.mslalith.githubmultiplatform.ui.common.topbar.ScreenAwareTopBar
import dev.mslalith.githubmultiplatform.ui.screens.repositorylist.RepositoryListScreenState.Failed
import dev.mslalith.githubmultiplatform.ui.screens.repositorylist.RepositoryListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.repositorylist.RepositoryListScreenState.Success
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_400
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class RepositoryListScreen : Screen {

    override val key: ScreenKey
        get() = super.key.also { println("key: $it") }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { RepositoryListScreenModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) { screenModel.fetchRepositories() }

        Scaffold(
            topBar = { ScreenAwareTopBar() }
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
                        RepositoriesList(repositories = repositories)
                    }
                }
            }
        }
    }
}

@Composable
private fun RepositoriesList(
    repositories: List<Repository>
) {
    LazyColumn {
        items(
            items = repositories,
            key = { it.id }
        ) { RepositoryItem(repository = it) }
    }
}

@Composable
private fun RepositoryItem(
    repository: Repository
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        KamelImage(
            resource = asyncPainterResource(data = repository.ownerAvatarUrl),
            contentDescription = null,
            modifier = Modifier
                .size(size = 56.dp)
                .clip(shape = CircleShape)
        )
        HorizontalSpace(space = 12.dp)
        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = repository.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            VerticalSpace(space = 4.dp)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = repository.name,
                    color = Bg_Gray_Dark_400
                )
                Text(
                    text = repository.ownerName,
                    color = Bg_Gray_Dark_400
                )
            }
        }
    }
}
