package dev.mslalith.githubmultiplatform.ui.screens.main.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.Octicons
import compose.icons.octicons.Pin24
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.data.model.ProfilePinnedRepository
import dev.mslalith.githubmultiplatform.ui.bottomsheets.ImageVectorIcon
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.StarsAndLanguage
import dev.mslalith.githubmultiplatform.ui.common.TabSection
import dev.mslalith.githubmultiplatform.ui.common.VerticalSpace
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_500
import dev.mslalith.githubmultiplatform.ui.theme.border
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
internal fun PinnedRepositoriesSection(
    pinnedRepositories: List<ProfilePinnedRepository>
) {
    TabSection(
        title = SharedRes.strings.pinned,
        leading = {
            ImageVectorIcon(
                icon = Octicons.Pin24,
                color = Bg_Gray_Dark_500
            )
        },
        content = { PinnedRepositoriesList(pinnedRepositories = pinnedRepositories) }
    )
}

@Composable
private fun PinnedRepositoriesList(
    pinnedRepositories: List<ProfilePinnedRepository>
) {
    BoxWithConstraints {
        LazyRow {
            item { HorizontalSpace(space = 12.dp) }
            items(
                items = pinnedRepositories,
                key = { it.id }
            ) { repository ->
                PinnedRepository(
                    pinnedRepository = repository,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
            item { HorizontalSpace(space = 12.dp) }
        }
    }
}

@Composable
private fun BoxWithConstraintsScope.PinnedRepository(
    modifier: Modifier = Modifier,
    pinnedRepository: ProfilePinnedRepository
) {
    Surface(
        modifier = modifier
            .width(width = maxWidth * 0.8f)
            .clip(shape = RoundedCornerShape(size = 12.dp))
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.border,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .clickable { },
        shadowElevation = 4.dp,
        tonalElevation = 0.2.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
        ) {
            RepoOwnerInfo(
                name = pinnedRepository.owner,
                avatarUrl = pinnedRepository.ownerAvatarUrl
            )
            VerticalSpace(space = 4.dp)
            Text(
                text = pinnedRepository.name,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            VerticalSpace(space = 4.dp)
            Text(
                text = pinnedRepository.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp
            )
            VerticalSpace(space = 16.dp)
            StarsAndLanguage(
                stars = pinnedRepository.stars,
                language = pinnedRepository.language
            )
        }
    }
}

@Composable
private fun RepoOwnerInfo(
    name: String,
    avatarUrl: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        KamelImage(
            resource = asyncPainterResource(data = avatarUrl),
            contentDescription = "",
            modifier = Modifier
                .size(size = 16.dp)
                .clip(shape = CircleShape)
        )
        HorizontalSpace(space = 12.dp)
        Text(
            text = name,
            color = Bg_Gray_Dark_500
        )
    }
}
