package dev.mslalith.githubmultiplatform.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.math.roundToLong

private const val SECOND = 1000L
private const val MINUTE = 60 * SECOND
private const val HOUR = 60 * MINUTE
private const val DAY = 24 * HOUR
private const val MONTH = 30 * DAY
private const val YEAR = 365 * DAY

private fun Long.within(value: Long): Long = (this * 1.0 / value).roundToLong()

fun LocalDateTime.timeAgo(): String {
    val diff = Clock.System.now() - toInstant(timeZone = TimeZone.UTC)
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

fun String.toLocalDateTime(): LocalDateTime {
    val (year, month, day) = this
        .substringBefore(delimiter = "T")
        .split('-')
        .map { it.toInt() }
        .let { if (it.size < 3) it + List(size = 3 - it.size) { 0 } else it }

    val (hour, minute, second) = this
        .substringAfter(delimiter = "T")
        .substringBefore(delimiter = "Z")
        .split(':')
        .map { it.toInt() }
        .let { if (it.size < 3) it + List(size = 3 - it.size) { 0 } else it }

    return LocalDateTime(year, month, day, hour, minute, second)
}
