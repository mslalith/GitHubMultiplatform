package dev.mslalith.githubmultiplatform.ui.screens.issueslist

import dev.mslalith.githubmultiplatform.data.model.Issue

sealed class IssuesListScreenState {
    object Loading : IssuesListScreenState()
    object Failed : IssuesListScreenState()
    data class Success(
        val issues: List<Issue>
    ) : IssuesListScreenState()
}
