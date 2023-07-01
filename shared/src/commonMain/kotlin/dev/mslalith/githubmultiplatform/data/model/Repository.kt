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
    repositories = nodes.orEmpty().filterNotNull().map {
        val ownerName = it.parent?.owner?.login ?: it.owner.login
        val ownerAvatarUrl = it.parent?.owner?.avatarUrl ?: it.owner.avatarUrl
        Repository(
            id = it.id,
            name = it.name,
            ownerName = ownerName,
            ownerAvatarUrl = ownerAvatarUrl.toString()
        )
    }
)
