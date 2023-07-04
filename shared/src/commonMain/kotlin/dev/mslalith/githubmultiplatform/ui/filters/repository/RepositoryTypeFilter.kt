package dev.mslalith.githubmultiplatform.ui.filters.repository

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.filters.base.FilterUiValue

sealed interface RepositoryTypeFilter : FilterUiValue {
    object All : RepositoryTypeFilter {
        override val stringResource: StringResource = SharedRes.strings.all
    }

    object Fork : RepositoryTypeFilter {
        override val stringResource: StringResource = SharedRes.strings.fork
    }

    object Private : RepositoryTypeFilter {
        override val stringResource: StringResource = SharedRes.strings.private_
    }
}
