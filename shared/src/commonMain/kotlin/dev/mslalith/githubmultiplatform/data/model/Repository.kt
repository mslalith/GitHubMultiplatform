package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.GetRepositoriesQuery
import dev.mslalith.githubmultiplatform.platform.CommonSerializable

data class Repository(
    val id: String,
    val name: String,
    val ownerName: String,
    val ownerAvatarUrl: String,
    val isFork: Boolean,
    val isPrivate: Boolean
) : CommonSerializable

fun GetRepositoriesQuery.Repositories.toPagedRepositories() = PagedRepositories(
    pageInfo = pageInfo.toPageInfo(),
    repositories = nodes.orEmpty().filterNotNull().map {
        val ownerName = it.parent?.owner?.login ?: it.owner.login
        val ownerAvatarUrl = it.parent?.owner?.avatarUrl ?: it.owner.avatarUrl
        Repository(
            id = it.id,
            name = it.name,
            ownerName = ownerName,
            ownerAvatarUrl = ownerAvatarUrl.toString(),
            isFork = it.isFork,
            isPrivate = it.isPrivate
        )
    }
)
