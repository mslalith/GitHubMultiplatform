package dev.mslalith.githubmultiplatform.model

data class PagedRepositories(
    val pageInfo: PageInfo,
    val repositories: List<Repository>
)
