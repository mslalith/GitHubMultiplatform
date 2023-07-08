package dev.mslalith.githubmultiplatform.data.model

import dev.mslalith.githubmultiplatform.type.IssueState
import dev.mslalith.githubmultiplatform.type.IssueStateReason
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.math.roundToLong

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
) {
    val createAgo: String
        get() {
            val diff = Clock.System.now() - createdAt.toInstant(timeZone = TimeZone.UTC)
            val millis = diff.inWholeMilliseconds
            return when {
                millis >= YEAR -> "${millis.within(YEAR)}y"
                millis >= MONTH -> "${millis.within(MONTH)}mo"
                millis >= DAY -> "${millis.within(DAY)}d"
                millis >= HOUR -> "${millis.within(HOUR)}h"
                millis >= MINUTE -> "${millis.within(MINUTE)}m"
                else -> "${millis.within(SECOND)}s"
            }
        }
}

private fun Long.within(value: Long): Long = (this * 1.0 / value).roundToLong()

private const val SECOND = 1000L
private const val MINUTE = 60 * SECOND
private const val HOUR = 60 * MINUTE
private const val DAY = 24 * HOUR
private const val MONTH = 30 * DAY
private const val YEAR = 365 * DAY
