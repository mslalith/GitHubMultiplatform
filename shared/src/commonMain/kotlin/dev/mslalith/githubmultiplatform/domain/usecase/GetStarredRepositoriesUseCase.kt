package dev.mslalith.githubmultiplatform.domain.usecase

import dev.mslalith.githubmultiplatform.GetStarredRepositoriesQuery
import dev.mslalith.githubmultiplatform.data.model.PageInfo
import dev.mslalith.githubmultiplatform.data.model.PagedStarredRepositories
import dev.mslalith.githubmultiplatform.data.model.StarredRepository
import dev.mslalith.githubmultiplatform.data.network.GitHubClient
import dev.mslalith.githubmultiplatform.domain.dto.toRepositoryLanguage
import dev.mslalith.githubmultiplatform.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject

class GetStarredRepositoriesUseCase : FlowUseCase.NoParams<PagedStarredRepositories>() {

    private val githubClient: GitHubClient by inject()

    override suspend fun run(): Flow<PagedStarredRepositories> = githubClient
        .getStarredRepositories()
        .map(GetStarredRepositoriesQuery.StarredRepositories::toPagedRepositories)
}

private fun GetStarredRepositoriesQuery.StarredRepositories.toPagedRepositories() = PagedStarredRepositories(
    pageInfo = pageInfo.toPageInfo(),
    repositories = nodes.orEmpty().filterNotNull().map {
        StarredRepository(
            id = it.id,
            name = it.name,
            description = it.description,
            ownerName = it.owner.login,
            ownerAvatarUrl = it.owner.avatarUrl.toString(),
            stars = it.stargazerCount,
            language = it.languages?.nodes?.firstOrNull()?.toRepositoryLanguage()
        )
    }
)

fun GetStarredRepositoriesQuery.PageInfo.toPageInfo() = PageInfo(
    startCursor = startCursor,
    endCursor = endCursor,
    hasPreviousPage = hasPreviousPage,
    hasNextPage = hasNextPage
)
