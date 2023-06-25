package dev.mslalith.githubmultiplatform.data.model

data class PagedRepositories(
    val pageInfo: PageInfo,
    val repositories: List<Repository>
)
