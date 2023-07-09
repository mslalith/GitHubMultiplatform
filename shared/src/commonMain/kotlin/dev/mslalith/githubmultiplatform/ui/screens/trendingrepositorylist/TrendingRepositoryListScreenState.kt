package dev.mslalith.githubmultiplatform.ui.screens.trendingrepositorylist

import dev.mslalith.githubmultiplatform.data.model.TrendingRepositories

sealed class TrendingRepositoryListScreenState {
    object Loading : TrendingRepositoryListScreenState()
    object Failed : TrendingRepositoryListScreenState()
    data class Success(
        val repositories: TrendingRepositories
    ) : TrendingRepositoryListScreenState()
}
