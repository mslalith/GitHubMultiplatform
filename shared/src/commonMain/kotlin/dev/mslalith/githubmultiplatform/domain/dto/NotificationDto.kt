package dev.mslalith.githubmultiplatform.domain.dto

import database.NotificationsTable
import dev.mslalith.githubmultiplatform.data.model.notification.Notification
import dev.mslalith.githubmultiplatform.data.model.notification.NotificationType
import dev.mslalith.githubmultiplatform.data.model.remote.NotificationRemote
import dev.mslalith.githubmultiplatform.extensions.toLocalDateTime

fun NotificationsTable.toNotification() = Notification(
    id = id,
    title = subject_title,
    updatedAt = updated_at.toLocalDateTime(),
    ownerName = repository_owner_login,
    repoName = repository_name,
    unread = unread.toBoolean(),
    type = NotificationType.fromString(type = subject_type)
)

fun NotificationRemote.toLocalNotification() = NotificationsTable(
    id = id,
    unread = unread,
    reason = reason,
    updated_at = updatedAt,
    last_read_at = lastReadAt,
    subject_title = subject.title,
    subject_url = subject.url ?: "",
    subject_latest_comment_url = subject.latestCommentUrl ?: "",
    subject_type = subject.type,
    repository_id = repository.id,
    repository_name = repository.name,
    repository_private = if (repository.private) 1 else 0,
    repository_owner_id = repository.owner.id,
    repository_owner_login = repository.owner.login,
    repository_owner_avatarUrl = repository.owner.avatarUrl
)
