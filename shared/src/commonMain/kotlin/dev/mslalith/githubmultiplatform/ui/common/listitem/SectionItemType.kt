package dev.mslalith.githubmultiplatform.ui.common.listitem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.Octicons
import compose.icons.octicons.CommentDiscussion16
import compose.icons.octicons.GitPullRequest16
import compose.icons.octicons.IssueOpened16
import compose.icons.octicons.Repo16
import compose.icons.octicons.Star16
import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Blue
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_400
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Green
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Purple
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Yellow

sealed class SectionItemType(
    val stringResource: StringResource,
    val icon: ImageVector,
    val iconBackgroundColor: Color
) {
    object Issues : SectionItemType(
        stringResource = SharedRes.strings.issues,
        icon = Octicons.IssueOpened16,
        iconBackgroundColor = Bg_Green,
    )

    object PullRequests : SectionItemType(
        stringResource = SharedRes.strings.pull_requests,
        icon = Octicons.GitPullRequest16,
        iconBackgroundColor = Bg_Blue,
    )

    object Discussions : SectionItemType(
        stringResource = SharedRes.strings.discussions,
        icon = Octicons.CommentDiscussion16,
        iconBackgroundColor = Bg_Purple,
    )

    object Repositories : SectionItemType(
        stringResource = SharedRes.strings.repositories,
        icon = Octicons.Repo16,
        iconBackgroundColor = Bg_Gray_Dark_400,
    )

    object StarredRepositories : SectionItemType(
        stringResource = SharedRes.strings.starred,
        icon = Octicons.Star16,
        iconBackgroundColor = Bg_Yellow,
    )
}
