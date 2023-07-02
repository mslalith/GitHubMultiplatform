package dev.mslalith.githubmultiplatform.domain.usecase

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.data.model.PageInfo
import dev.mslalith.githubmultiplatform.data.model.PagedRepositories
import dev.mslalith.githubmultiplatform.data.model.Repository
import dev.mslalith.githubmultiplatform.data.network.GitHubClient
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

private fun GetRepositoriesQuery.Repositories.toPagedRepositories() = PagedRepositories(
    pageInfo = pageInfo.toPageInfo(),
    repositories = nodes.orEmpty().filterNotNull().map {
        val ownerName = it.parent?.owner?.login ?: it.owner.login
        val ownerAvatarUrl = it.parent?.owner?.avatarUrl ?: it.owner.avatarUrl
        Repository(
            id = it.id,
            name = it.name,
            ownerName = ownerName,
            ownerAvatarUrl = ownerAvatarUrl.toString(),
            isFork = it.isFork,
            isPrivate = it.isPrivate
        )
    }
)

fun GetRepositoriesQuery.PageInfo.toPageInfo() = PageInfo(
    startCursor = startCursor,
    endCursor = endCursor,
    hasPreviousPage = hasPreviousPage,
    hasNextPage = hasNextPage
)
