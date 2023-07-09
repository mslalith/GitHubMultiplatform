package dev.mslalith.githubmultiplatform.data.model

data class PagedRepositories(
    val pageInfo: PageInfo,
    val repositories: Repositories
)

data class PagedStarredRepositories(
    val pageInfo: PageInfo,
    val repositories: StarredRepositories
)

data class PagedIssues(
    val pageInfo: PageInfo,
    val issues: Issues
)
