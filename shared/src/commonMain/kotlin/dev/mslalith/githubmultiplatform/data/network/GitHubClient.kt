package dev.mslalith.githubmultiplatform.data.network

import dev.mslalith.githubmultiplatform.GetIssuesQuery
import dev.mslalith.githubmultiplatform.GetProfileQuery
import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.GetStarredRepositoriesQuery
import kotlinx.coroutines.flow.Flow

interface GitHubClient {
    fun getRepositories(): Flow<GetRepositoriesQuery.Repositories>
    fun getStarredRepositories(): Flow<GetStarredRepositoriesQuery.StarredRepositories>
    fun getProfileTabInfo(login: String): Flow<GetProfileQuery.Data>
    fun getIssues(): Flow<GetIssuesQuery.Issues>
}
