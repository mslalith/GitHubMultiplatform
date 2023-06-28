package dev.mslalith.githubmultiplatform.data.model.profile

import dev.mslalith.githubmultiplatform.data.model.ProfilePinnedRepository

data class ProfileTabState(
    val name: String,
    val login: String,
    val avatarUrl: String,
    val pronouns: String?,
    val email: String,
    val followersCount: Int,
    val followingCount: Int,
    val pinnedRepositories: List<ProfilePinnedRepository>,
    val repositoriesCount: Int,
    val starredRepositoriesCount: Int,
)
