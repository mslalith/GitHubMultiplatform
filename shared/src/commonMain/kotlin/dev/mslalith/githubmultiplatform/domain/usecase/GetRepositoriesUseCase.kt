package dev.mslalith.githubmultiplatform.domain.usecase

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.model.PagedRepositories
import dev.mslalith.githubmultiplatform.model.toPagedRepositories
import dev.mslalith.githubmultiplatform.network.GitHubClient
import dev.mslalith.githubmultiplatform.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject

class GetRepositoriesUseCase : FlowUseCase.NoParams<PagedRepositories>() {

    private val githubClient: GitHubClient by inject()

    override suspend fun run(): Flow<PagedRepositories> = githubClient
        .getRepositories()
        .map(GetRepositoriesQuery.Repositories::toPagedRepositories)
}
