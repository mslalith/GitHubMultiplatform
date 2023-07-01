package dev.mslalith.githubmultiplatform.ui.screens.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.Octicons
import compose.icons.octicons.Mail16
import compose.icons.octicons.Person16
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.common.Dot
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.VerticalSpace
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_400
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_500
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
internal fun UserProfileInfo(
    modifier: Modifier = Modifier,
    name: String,
    login: String,
    avatarUrl: String,
    pronouns: String?,
    email: String,
    followersCount: Int,
    followingCount: Int,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            KamelImage(
                resource = asyncPainterResource(data = avatarUrl),
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
                    text = name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                VerticalSpace(space = 4.dp)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = login,
                        color = Bg_Gray_Dark_400
                    )
                    if (pronouns != null) {
                        Dot(
                            size = 4.dp,
                            color = Bg_Gray_Dark_400,
                            horizontalSpace = 8.dp
                        )
                        Text(
                            text = pronouns,
                            color = Bg_Gray_Dark_400
                        )
                    }
                }
            }
        }
        VerticalSpace(space = 12.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberVectorPainter(image = Octicons.Mail16),
                contentDescription = "",
                colorFilter = ColorFilter.tint(color = Bg_Gray_Dark_500)
            )
            HorizontalSpace(space = 12.dp)
            Text(
                text = email,
                fontWeight = FontWeight.Bold
            )
        }
        VerticalSpace(space = 12.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberVectorPainter(image = Octicons.Person16),
                contentDescription = "",
                colorFilter = ColorFilter.tint(color = Bg_Gray_Dark_500)
            )
            HorizontalSpace(space = 12.dp)
            FollowText(
                count = followersCount,
                stringRes = SharedRes.strings.followers_
            )
            Dot(
                size = 4.dp,
                color = MaterialTheme.colorScheme.onBackground,
                horizontalSpace = 8.dp
            )
            FollowText(
                count = followingCount,
                stringRes = SharedRes.strings.following_
            )
        }
    }
}

@Composable
private fun FollowText(
    count: Int,
    stringRes: StringResource
) {
    val text = stringResource(resource = stringRes)
    Text(
        text = remember(key1 = count) {
            buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(count.toString())
                }
                append(" ")
                append(text)
            }
        }
    )
}
