package dev.mslalith.githubmultiplatform.usecase

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.network.GitHubClient
import kotlinx.coroutines.flow.Flow

class GetRepositoriesUseCase(
    private val githubClient: GitHubClient
) {
    suspend operator fun invoke(): Flow<GetRepositoriesQuery.Repositories> {
        return githubClient.getRepositories()
    }
}
