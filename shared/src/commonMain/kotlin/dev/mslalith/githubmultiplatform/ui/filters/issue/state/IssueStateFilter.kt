package dev.mslalith.githubmultiplatform.ui.filters.issue.state

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes

enum class IssueStateFilter(val stringResource: StringResource) {
    ALL(stringResource = SharedRes.strings.all),
    OPEN(stringResource = SharedRes.strings.open),
    CLOSED(stringResource = SharedRes.strings.closed)
}
