package dev.mslalith.githubmultiplatform.network

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import kotlinx.coroutines.flow.Flow

interface GitHubClient {
    suspend fun getRepositories(): Flow<GetRepositoriesQuery.Repositories>
}
