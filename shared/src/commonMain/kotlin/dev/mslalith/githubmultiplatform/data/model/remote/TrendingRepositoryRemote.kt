package dev.mslalith.githubmultiplatform.data.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingRepositoryRemote(
    @SerialName("author")
    val author: String,
    @SerialName("avatar")
    val avatarUrl: String,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String?,
    @SerialName("stars")
    val stars: Int,
    @SerialName("language")
    val languageName: String?,
    @SerialName("languageColor")
    val languageColor: String?
)
