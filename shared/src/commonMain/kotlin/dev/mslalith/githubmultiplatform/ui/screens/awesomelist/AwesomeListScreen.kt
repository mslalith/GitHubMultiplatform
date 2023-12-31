package dev.mslalith.githubmultiplatform.ui.screens.awesomelist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import compose.icons.Octicons
import compose.icons.octicons.Person16
import compose.icons.octicons.Star16
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.data.model.AwesomeListRepositories
import dev.mslalith.githubmultiplatform.data.model.AwesomeListRepository
import dev.mslalith.githubmultiplatform.ui.common.HorizontalLine
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.IconInfoText
import dev.mslalith.githubmultiplatform.ui.common.StarsAndLanguage
import dev.mslalith.githubmultiplatform.ui.common.VerticalSpace
import dev.mslalith.githubmultiplatform.ui.common.button.StarButton
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenTitle
import dev.mslalith.githubmultiplatform.ui.common.topbar.ScreenAwareTopBar
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Failed
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Loading
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Success
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_400
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlin.jvm.Transient

class AwesomeListScreen : Screen, ScreenTitle {

    @Transient
    override val titleResource: StringResource = SharedRes.strings.awesome_lists

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { AwesomeListScreenModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) { screenModel.fetchAwesomeList() }

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
                            val repositories = (state as Success).value
                            AwesomeRepositoriesList(repositories = repositories)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AwesomeRepositoriesList(
    repositories: AwesomeListRepositories
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            count = repositories.size,
            key = { repositories[it].id },
        ) { index ->
            AwesomeRepositoryItem(
                repository = repositories[index],
                onClick = {}
            )
            if (index != repositories.lastIndex) {
                VerticalSpace(space = 12.dp)
                HorizontalLine(height = 1.dp)
                VerticalSpace(space = 12.dp)
            }
        }
    }
}

@Composable
private fun AwesomeRepositoryItem(
    repository: AwesomeListRepository,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        KamelImage(
            resource = asyncPainterResource(data = repository.graphImageUrl),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
        VerticalSpace(space = 12.dp)
        AwesomeRepositoryContent(
            repository = repository,
            onStarClick = {}
        )
    }
}

@Composable
private fun AwesomeRepositoryContent(
    repository: AwesomeListRepository,
    onStarClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        UserInfo(
            ownerName = repository.ownerName,
            avatarUrl = repository.ownerAvatarUrl,
            repositoryName = repository.name
        )

        if (repository.description != null) {
            VerticalSpace(space = 12.dp)
            Text(
                text = repository.description,
                color = Bg_Gray_Dark_400,
                fontSize = 16.sp
            )
        }

        VerticalSpace(space = 12.dp)
        StarsAndLanguage(
            stars = repository.stars,
            language = repository.language
        )

        VerticalSpace(space = 12.dp)
        IconInfoText(
            text = stringResource(
                resource = SharedRes.plurals.contributors,
                quantity = repository.contributorsCount,
                repository.contributorsCount
            ),
            icon = Octicons.Person16
        )

        VerticalSpace(space = 12.dp)
        StarButton(
            text = SharedRes.strings.star,
            textAllCaps = true,
            icon = Octicons.Star16,
            onClick = onStarClick
        )
    }
}

@Composable
private fun UserInfo(
    ownerName: String,
    avatarUrl: String,
    repositoryName: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        KamelImage(
            resource = asyncPainterResource(data = avatarUrl),
            contentDescription = "",
            modifier = Modifier
                .size(size = 36.dp)
                .clip(shape = CircleShape)
        )
        HorizontalSpace(space = 12.dp)
        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = repositoryName,
                fontWeight = FontWeight.SemiBold
            )
            VerticalSpace(space = 2.dp)
            Text(
                text = ownerName,
                color = Bg_Gray_Dark_400,
                fontSize = 14.sp
            )
        }
    }
}
