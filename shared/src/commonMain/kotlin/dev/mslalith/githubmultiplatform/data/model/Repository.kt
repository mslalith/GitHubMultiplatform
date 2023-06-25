package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery

data class Repository(
    val id: String,
    val name: String
)

fun GetRepositoriesQuery.Repositories.toPagedRepositories() = PagedRepositories(
    pageInfo = pageInfo.toPageInfo(),
    repositories = nodes.orEmpty().filterNotNull().map {
        Repository(
            id = it.id,
            name = it.name
        )
    }
)
