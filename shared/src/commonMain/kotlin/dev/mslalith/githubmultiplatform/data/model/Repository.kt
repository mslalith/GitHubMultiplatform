package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.platform.CommonSerializable
import kotlinx.datetime.LocalDateTime

typealias Repositories = List<Repository>

data class Repository(
    val id: String,
    val name: String,
    val ownerName: String,
    val ownerAvatarUrl: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val isFork: Boolean,
    val isPrivate: Boolean
) : CommonSerializable
