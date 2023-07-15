package dev.mslalith.githubmultiplatform.data.network

import dev.mslalith.githubmultiplatform.GetAwesomeListQuery
import dev.mslalith.githubmultiplatform.GetIssuesQuery
import dev.mslalith.githubmultiplatform.GetProfileQuery
import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.GetStarredRepositoriesQuery
import dev.mslalith.githubmultiplatform.data.model.remote.NotificationRemote
import dev.mslalith.githubmultiplatform.data.model.remote.TrendingRepositoryRemote
import kotlinx.coroutines.flow.Flow

interface GitHubClient {
    fun getRepositories(): Flow<GetRepositoriesQuery.Repositories>
    fun getStarredRepositories(): Flow<GetStarredRepositoriesQuery.StarredRepositories>
    fun getProfileTabInfo(login: String): Flow<GetProfileQuery.Data>
    fun getIssues(): Flow<GetIssuesQuery.Issues>
    fun getAwesomeList(): Flow<GetAwesomeListQuery.Search>
    suspend fun getTrendingRepositories(): List<TrendingRepositoryRemote>
    suspend fun getNotifications(): List<NotificationRemote>
}
