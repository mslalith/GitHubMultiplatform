package dev.mslalith.githubmultiplatform.ui.filters.issue.visibility

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes

enum class IssueVisibilityFilter(val stringResource: StringResource) {
    ALL(stringResource = SharedRes.strings.show_all),
    PRIVATE(stringResource = SharedRes.strings.private_repositories_only),
    PUBLIC(stringResource = SharedRes.strings.public_repositories_only)
}
