package dev.mslalith.githubmultiplatform.usecase

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.model.Repository
import dev.mslalith.githubmultiplatform.model.toRepository
import dev.mslalith.githubmultiplatform.network.GitHubClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRepositoriesUseCase(
    private val githubClient: GitHubClient
) {
    suspend operator fun invoke(): Flow<List<Repository>> = githubClient
        .getRepositories()
        .map(GetRepositoriesQuery.Repositories::toRepository)
}
