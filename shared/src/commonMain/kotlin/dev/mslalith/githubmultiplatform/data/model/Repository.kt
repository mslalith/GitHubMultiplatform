package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery

data class Repository(
    val id: String,
    val name: String,
    val ownerName: String,
    val ownerAvatarUrl: String
)

fun GetRepositoriesQuery.Repositories.toPagedRepositories() = PagedRepositories(
    pageInfo = pageInfo.toPageInfo(),
    repositories = nodes.orEmpty().filterNotNull().mapNotNull {
        val owner = it.parent?.owner ?: return@mapNotNull null
        Repository(
            id = it.id,
            name = it.name,
            ownerName = owner.login,
            ownerAvatarUrl = owner.avatarUrl.toString()
        )
    }
)
