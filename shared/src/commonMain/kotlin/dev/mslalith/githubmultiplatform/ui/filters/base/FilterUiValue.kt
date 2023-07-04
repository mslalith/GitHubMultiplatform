package dev.mslalith.githubmultiplatform.ui.filters.base

import dev.icerock.moko.resources.StringResource

interface FilterUiValue {
    val stringResource: StringResource
    val selectedStringResource: StringResource
        get() = stringResource
}
