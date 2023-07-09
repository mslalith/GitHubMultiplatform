package dev.mslalith.githubmultiplatform.domain.dto

import database.TrendingRepositoriesTable
import dev.mslalith.githubmultiplatform.data.model.RepositoryLanguage
import dev.mslalith.githubmultiplatform.data.model.TrendingRepository
import dev.mslalith.githubmultiplatform.data.model.remote.TrendingRepositoryRemote

fun TrendingRepositoriesTable.toTrendingRepository() = TrendingRepository(
    ownerName = author,
    ownerAvatarUrl = author_avatar,
    name = repo_name,
    description = description,
    stars = stars.toInt(),
    language = if (language != null && language_color != null ) RepositoryLanguage(
        id = language,
        name = language,
        color = language_color
    ) else null
)

fun TrendingRepository.toTrendingRepositoryTable() = TrendingRepositoriesTable(
    author = ownerName,
    author_avatar = ownerAvatarUrl,
    repo_name = name,
    description = description,
    language = language?.name,
    language_color = language?.color,
    stars = stars.toLong()
)

fun TrendingRepositoryRemote.toTrendingRepository() = TrendingRepository(
    ownerName = author,
    ownerAvatarUrl = avatarUrl,
    name = name,
    description = description,
    stars = stars,
    language = if (languageName != null && languageColor != null ) RepositoryLanguage(
        id = languageName,
        name = languageName,
        color = languageColor
    ) else null
)
