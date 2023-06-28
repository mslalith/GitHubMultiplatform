package dev.mslalith.githubmultiplatform.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoggedInUser(
    val id: Long,
    val login: String,
    val name: String
)
