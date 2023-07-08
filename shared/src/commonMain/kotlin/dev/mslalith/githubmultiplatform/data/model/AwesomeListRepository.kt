package dev.mslalith.githubmultiplatform.data.model

typealias AwesomeListRepositories = List<AwesomeListRepository>

data class AwesomeListRepository(
    val id: String,
    val ownerName: String,
    val ownerAvatarUrl: String,
    val name: String,
    val description: String?,
    val stars: Int,
    val graphImageUrl: String,
    val language: RepositoryLanguage?
)
