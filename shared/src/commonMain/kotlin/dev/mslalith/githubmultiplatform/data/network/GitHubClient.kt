package dev.mslalith.githubmultiplatform.data.network

import dev.mslalith.githubmultiplatform.GetProfileQuery
import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import kotlinx.coroutines.flow.Flow

interface GitHubClient {
    suspend fun getRepositories(): Flow<GetRepositoriesQuery.Repositories>
    suspend fun getProfileTabInfo(login: String): Flow<GetProfileQuery.Data>
}
