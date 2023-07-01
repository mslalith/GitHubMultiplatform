package dev.mslalith.githubmultiplatform.ui.screens.main.home

import dev.mslalith.githubmultiplatform.data.model.Repository

sealed class HomeTabState {
    object Loading : HomeTabState()
    object Failed : HomeTabState()
    data class Success(
        val repositories: List<Repository>
    ) : HomeTabState()
}
