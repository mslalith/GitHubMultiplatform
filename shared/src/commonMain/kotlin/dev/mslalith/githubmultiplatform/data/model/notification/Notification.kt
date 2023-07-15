package dev.mslalith.githubmultiplatform.data.model.notification

import kotlinx.datetime.LocalDateTime

typealias Notifications = List<Notification>

data class Notification(
    val id: String,
    val title: String,
    val updatedAt: LocalDateTime,
    val ownerName: String,
    val repoName: String,
    val unread: Boolean,
    val type: NotificationType
)
