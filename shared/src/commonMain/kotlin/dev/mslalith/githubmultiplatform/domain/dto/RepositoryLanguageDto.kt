package dev.mslalith.githubmultiplatform.domain.dto

import dev.mslalith.githubmultiplatform.GetProfileQuery
import dev.mslalith.githubmultiplatform.data.model.RepositoryLanguage

fun GetProfileQuery.Node1.toRepositoryLanguage() = RepositoryLanguage(
    id = id,
    name = name,
    color = color ?: ""
)

fun GetProfileQuery.Languages.toRepositoryLanguages(): List<RepositoryLanguage> {
    return nodes?.mapNotNull { it?.toRepositoryLanguage() }.orEmpty()
}
