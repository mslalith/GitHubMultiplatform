package dev.mslalith.githubmultiplatform.domain.dto

import dev.mslalith.githubmultiplatform.GetIssuesQuery
import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.GetStarredRepositoriesQuery
import dev.mslalith.githubmultiplatform.data.model.PageInfo

fun GetRepositoriesQuery.PageInfo.toPageInfo() = PageInfo(
    startCursor = startCursor,
    endCursor = endCursor,
    hasPreviousPage = hasPreviousPage,
    hasNextPage = hasNextPage
)

fun GetStarredRepositoriesQuery.PageInfo.toPageInfo() = PageInfo(
    startCursor = startCursor,
    endCursor = endCursor,
    hasPreviousPage = hasPreviousPage,
    hasNextPage = hasNextPage
)

fun GetIssuesQuery.PageInfo.toPageInfo() = PageInfo(
    startCursor = startCursor,
    endCursor = endCursor,
    hasPreviousPage = hasPreviousPage,
    hasNextPage = hasNextPage
)
