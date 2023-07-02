package dev.mslalith.githubmultiplatform.ui.filters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.resources.StringResource

abstract class FilterStateHolder<T>(
    private val initial: T
) {
    var selectedType by mutableStateOf(value = initial)
        private set

    val isInitial: Boolean
        get() = selectedType == initial

    abstract fun mapToStringResource(value: T): StringResource

    fun update(value: T) {
        selectedType = value
    }

    fun reset() = update(value = initial)
}
