package dev.mslalith.githubmultiplatform.ui.filters.base

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.platform.CommonSerializable

interface FilterUiValue : CommonSerializable {
    val stringResource: StringResource
    val selectedStringResource: StringResource
        get() = stringResource
}
