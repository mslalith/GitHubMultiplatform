package dev.mslalith.githubmultiplatform.domain.usecase

import dev.mslalith.githubmultiplatform.GetAwesomeListQuery
import dev.mslalith.githubmultiplatform.data.model.AwesomeListRepositories
import dev.mslalith.githubmultiplatform.data.model.AwesomeListRepository
import dev.mslalith.githubmultiplatform.data.network.GitHubClient
import dev.mslalith.githubmultiplatform.domain.dto.toRepositoryLanguage
import dev.mslalith.githubmultiplatform.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject

class GetAwesomeListUseCase : FlowUseCase.NoParams<AwesomeListRepositories>() {

    private val githubClient: GitHubClient by inject()

    override fun run(): Flow<AwesomeListRepositories> = githubClient
        .getAwesomeList()
        .map(GetAwesomeListQuery.Search::toAwesomeListRepositories)
}

private fun GetAwesomeListQuery.Search.toAwesomeListRepositories() = nodes
    .orEmpty()
    .filterNotNull()
    .mapNotNull { it.onRepository }
    .map {
        AwesomeListRepository(
            id = it.id,
            ownerName = it.owner.login,
            ownerAvatarUrl = it.owner.avatarUrl.toString(),
            name = it.name,
            description = it.description,
            stars = it.stargazerCount,
            graphImageUrl = it.openGraphImageUrl.toString(),
            language = it.languages?.nodes?.firstOrNull()?.toRepositoryLanguage()
        )
    }
