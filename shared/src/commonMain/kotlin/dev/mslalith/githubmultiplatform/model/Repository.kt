package dev.mslalith.githubmultiplatform.model

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery

data class Repository(
    val cursor: String,
    val name: String
)

fun GetRepositoriesQuery.Repositories.toRepository() = edgesFilterNotNull().orEmpty().mapNotNull {
    val node = it.node ?: return@mapNotNull null
    Repository(
        cursor = it.cursor,
        name = node.name
    )
}
