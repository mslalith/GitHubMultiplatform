package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.type.IssueState
import dev.mslalith.githubmultiplatform.type.IssueStateReason
import kotlinx.datetime.LocalDateTime

typealias Issues = List<Issue>

data class Issue(
    val id: String,
    val title: String,
    val repoName: String,
    val repoAuthor: String,
    val isRepoPrivate: Boolean,
    val number: Int,
    val totalComments: Int,
    val assigneeAvatarUrl: String?,
    val createdAt: LocalDateTime,
    val state: IssueState,
    val stateReason: IssueStateReason?,
    val isClosed: Boolean
)
