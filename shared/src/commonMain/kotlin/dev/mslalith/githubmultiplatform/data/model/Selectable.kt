package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.platform.CommonSerializable

data class Selectable<T>(
    val value: T,
    val isSelected: Boolean
) : CommonSerializable
