package dev.mslalith.githubmultiplatform.ui.filters.base

abstract class FilterState<T, UI>(
    initial: T
) : FilterStateHolder<T>(initial = initial) {

    abstract val allTypes: List<T>

    protected abstract fun mapToUi(value: T): UI

    fun listForUi(): List<UI> = allTypes.map(::mapToUi)
}
