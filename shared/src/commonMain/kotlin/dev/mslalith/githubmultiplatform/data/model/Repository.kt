package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.platform.CommonSerializable

data class Repository(
    val id: String,
    val name: String,
    val ownerName: String,
    val ownerAvatarUrl: String,
    val isFork: Boolean,
    val isPrivate: Boolean
) : CommonSerializable
