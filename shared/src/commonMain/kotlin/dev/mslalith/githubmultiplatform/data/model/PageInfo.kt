package dev.mslalith.githubmultiplatform.data.model

data class PageInfo(
    val startCursor: String?,
    val endCursor: String?,
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)
