package dev.mslalith.githubmultiplatform.data.model

import dev.icerock.moko.resources.StringResource

abstract class Selectable<T> {
    abstract val value: T
    abstract val isSelected: Boolean

    abstract fun text(): StringResource
}
