package dev.mslalith.githubmultiplatform.ui.filters.issue.state

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.filters.base.FilterUiValue

sealed interface IssueStateFilter : FilterUiValue {
    object All : IssueStateFilter {
        override val stringResource: StringResource = SharedRes.strings.all
    }

    object Open : IssueStateFilter {
        override val stringResource: StringResource = SharedRes.strings.open
    }

    object Closed : IssueStateFilter {
        override val stringResource: StringResource = SharedRes.strings.closed
    }
}
