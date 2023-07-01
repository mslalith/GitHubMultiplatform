package dev.mslalith.githubmultiplatform.ui.common.listitem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.Octicons
import compose.icons.octicons.Repo16
import compose.icons.octicons.Star16
import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_400
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Yellow

sealed class SectionItemType(
    val stringResource: StringResource,
    val icon: ImageVector,
    val iconBackgroundColor: Color
) {
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
