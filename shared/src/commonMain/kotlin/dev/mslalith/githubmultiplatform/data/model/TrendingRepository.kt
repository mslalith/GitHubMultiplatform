package dev.mslalith.githubmultiplatform.data.model

typealias TrendingRepositories = List<TrendingRepository>

data class TrendingRepository(
    val ownerName: String,
    val ownerAvatarUrl: String,
    val name: String,
    val description: String?,
    val stars: Int,
    val language: RepositoryLanguage?
)
