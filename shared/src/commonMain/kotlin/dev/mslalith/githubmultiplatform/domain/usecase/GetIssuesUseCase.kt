package dev.mslalith.githubmultiplatform.domain.usecase

import dev.mslalith.githubmultiplatform.GetIssuesQuery
import dev.mslalith.githubmultiplatform.data.model.Issue
import dev.mslalith.githubmultiplatform.data.model.PagedIssues
import dev.mslalith.githubmultiplatform.data.network.GitHubClient
import dev.mslalith.githubmultiplatform.domain.dto.toPageInfo
import dev.mslalith.githubmultiplatform.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.koin.core.component.inject

class GetIssuesUseCase : FlowUseCase.NoParams<PagedIssues>() {

    private val githubClient: GitHubClient by inject()

    override fun run(): Flow<PagedIssues> = githubClient
        .getIssues()
        .mapNotNull { it.toPagedIssues() }
}

private fun GetIssuesQuery.Issues.toPagedIssues() = PagedIssues(
    pageInfo = pageInfo.toPageInfo(),
    issues = nodes.orEmpty().filterNotNull().map(GetIssuesQuery.Node::toIssue)
)

private fun GetIssuesQuery.Node.toIssue() = Issue(
    id = id,
    title = title,
    repoName = repository.name,
    repoAuthor = repository.owner.login,
    isRepoPrivate = repository.isPrivate,
    number = number,
    totalComments = comments.totalCount,
    assigneeAvatarUrl = assignees.nodes?.firstOrNull()?.avatarUrl?.toString(),
    state = state,
    stateReason = stateReason,
    isClosed = closed
)
