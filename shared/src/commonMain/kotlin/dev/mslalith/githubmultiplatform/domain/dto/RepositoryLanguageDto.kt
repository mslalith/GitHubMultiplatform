package dev.mslalith.githubmultiplatform.domain.dto

import dev.mslalith.githubmultiplatform.GetAwesomeListQuery
import dev.mslalith.githubmultiplatform.GetProfileQuery
import dev.mslalith.githubmultiplatform.GetStarredRepositoriesQuery
import dev.mslalith.githubmultiplatform.data.model.RepositoryLanguage

fun GetProfileQuery.Node1.toRepositoryLanguage() = RepositoryLanguage(
    id = id,
    name = name,
    color = color ?: ""
)

fun GetStarredRepositoriesQuery.Node1.toRepositoryLanguage() = RepositoryLanguage(
    id = id,
    name = name,
    color = color ?: ""
)

fun GetAwesomeListQuery.Node1.toRepositoryLanguage() = RepositoryLanguage(
    id = id,
    name = name,
    color = color ?: ""
)
