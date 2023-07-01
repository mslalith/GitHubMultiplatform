package dev.mslalith.githubmultiplatform.ui.screens.repositorylist

import dev.mslalith.githubmultiplatform.data.model.Repository

sealed class RepositoryListScreenState {
    object Loading : RepositoryListScreenState()
    object Failed : RepositoryListScreenState()
    data class Success(
        val repositories: List<Repository>
    ) : RepositoryListScreenState()
}
