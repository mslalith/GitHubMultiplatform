package dev.mslalith.githubmultiplatform.ui.state

sealed class UiState

sealed class CommonState<out S> : UiState() {
    object Loading : CommonState<Nothing>()
    object Failed : CommonState<Nothing>()
    data class Success<T>(val value: T) : CommonState<T>()
}
