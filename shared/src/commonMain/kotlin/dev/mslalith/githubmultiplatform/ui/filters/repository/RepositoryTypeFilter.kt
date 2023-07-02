package dev.mslalith.githubmultiplatform.ui.filters.repository

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes

enum class RepositoryTypeFilter(val stringResource: StringResource) {
    ALL(stringResource = SharedRes.strings.all),
    FORK(stringResource = SharedRes.strings.fork),
    PRIVATE(stringResource = SharedRes.strings.private_)
}
