package dev.mslalith.githubmultiplatform.data.model.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationRemote(
    @SerialName("id")
    val id: String,
    @SerialName("unread")
    val unread: String,
    @SerialName("reason")
    val reason: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("last_read_at")
    val lastReadAt: String?,
    @SerialName("subject")
    val subject: NotificationSubjectRemote,
    @SerialName("repository")
    val repository: NotificationRepositoryRemote
)

@Serializable
data class NotificationSubjectRemote(
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String?,
    @SerialName("latest_comment_url")
    val latestCommentUrl: String?,
    @SerialName("type")
    val type: String
)

@Serializable
data class NotificationRepositoryRemote(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("private")
    val private: Boolean,
    @SerialName("owner")
    val owner: NotificationRepositoryOwnerRemote
)

@Serializable
data class NotificationRepositoryOwnerRemote(
    @SerialName("id")
    val id: Long,
    @SerialName("login")
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String
)
