package dev.mslalith.githubmultiplatform.data.model

data class ProfilePinnedRepository(
    val id: String,
    val owner: String,
    val name: String,
    val description: String,
    val stars: Int,
    val language: RepositoryLanguage?
)
