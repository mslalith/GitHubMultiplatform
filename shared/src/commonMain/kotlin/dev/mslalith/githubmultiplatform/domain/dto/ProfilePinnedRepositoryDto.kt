package dev.mslalith.githubmultiplatform.domain.dto

import dev.mslalith.githubmultiplatform.GetProfileQuery
import dev.mslalith.githubmultiplatform.data.model.ProfilePinnedRepository

fun GetProfileQuery.Node.toProfilePinnedRepository(): ProfilePinnedRepository? {
    val repo = onRepository ?: return null
    return ProfilePinnedRepository(
        id = repo.id,
        owner = repo.owner.login,
        name = repo.name,
        description = repo.description ?: "",
        stars = repo.stargazerCount,
        language = repo.languages?.nodes?.firstOrNull()?.toRepositoryLanguage()
    )
}

fun GetProfileQuery.PinnableItems.toProfilePinnedRepositories(): List<ProfilePinnedRepository> {
    return nodes?.mapNotNull { it?.toProfilePinnedRepository() }.orEmpty()
}
