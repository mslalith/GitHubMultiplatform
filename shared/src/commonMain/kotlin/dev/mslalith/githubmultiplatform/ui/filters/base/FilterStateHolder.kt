package dev.mslalith.githubmultiplatform.ui.filters.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.mslalith.githubmultiplatform.platform.CommonSerializable
import kotlin.jvm.Transient

abstract class FilterStateHolder<T>(
    private val initial: T
) : CommonSerializable where T : FilterUiValue {

    @delegate:Transient
    var selectedType by mutableStateOf(value = initial)
        private set

    val isInitial: Boolean
        get() = selectedType == initial

    fun update(value: T) {
        selectedType = value
    }

    fun reset() = update(value = initial)
}
