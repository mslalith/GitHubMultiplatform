package dev.mslalith.githubmultiplatform.ui.filters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class FilterStateHolder<T>(
    private val initial: T
) {
    var selectedType by mutableStateOf(value = initial)
        private set

    val isInitial: Boolean
        get() = selectedType == initial

    fun update(string: T) {
        selectedType = string
    }
}
