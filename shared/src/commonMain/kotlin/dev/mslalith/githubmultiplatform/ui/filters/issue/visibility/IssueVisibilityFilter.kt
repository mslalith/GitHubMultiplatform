package dev.mslalith.githubmultiplatform.ui.filters.issue.visibility

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.filters.base.FilterUiValue

sealed interface IssueVisibilityFilter : FilterUiValue {
    object All : IssueVisibilityFilter {
        override val stringResource: StringResource = SharedRes.strings.all
        override val selectedStringResource: StringResource = SharedRes.strings.show_all
    }

    object Private : IssueVisibilityFilter {
        override val stringResource: StringResource = SharedRes.strings.private_repositories_only
    }

    object Public : IssueVisibilityFilter {
        override val stringResource: StringResource = SharedRes.strings.public_repositories_only
    }
}
