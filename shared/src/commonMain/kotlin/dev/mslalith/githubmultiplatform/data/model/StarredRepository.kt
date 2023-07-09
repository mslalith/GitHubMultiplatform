package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.platform.CommonSerializable

typealias StarredRepositories = List<StarredRepository>

data class StarredRepository(
    val id: String,
    val name: String,
    val description: String?,
    val ownerName: String,
    val ownerAvatarUrl: String,
    val stars: Int,
    val language: RepositoryLanguage?
) : CommonSerializable
