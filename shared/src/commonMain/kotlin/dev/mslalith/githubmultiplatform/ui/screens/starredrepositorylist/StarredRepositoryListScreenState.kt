package dev.mslalith.githubmultiplatform.ui.screens.starredrepositorylist

import dev.mslalith.githubmultiplatform.data.model.StarredRepository

sealed class StarredRepositoryListScreenState {
    object Loading : StarredRepositoryListScreenState()
    object Failed : StarredRepositoryListScreenState()
    data class Success(
        val repositories: List<StarredRepository>
    ) : StarredRepositoryListScreenState()
}
