package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery

data class PageInfo(
    val startCursor: String?,
    val endCursor: String?,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)

fun GetRepositoriesQuery.PageInfo.toPageInfo() = PageInfo(
    startCursor = startCursor,
    endCursor = endCursor,
    hasPreviousPage = hasPreviousPage,
    hasNextPage = hasNextPage
)
