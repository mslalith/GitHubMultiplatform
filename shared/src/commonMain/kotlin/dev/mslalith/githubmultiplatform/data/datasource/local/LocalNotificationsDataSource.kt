package dev.mslalith.githubmultiplatform.data.datasource.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.NotificationsTable
import dev.mslalith.githubmultiplatform.data.model.notification.Notifications
import dev.mslalith.githubmultiplatform.database.GitHubDatabase
import dev.mslalith.githubmultiplatform.domain.dto.toNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LocalNotificationsDataSource : KoinComponent {

    private val gitHubDatabase by inject<GitHubDatabase>()
    private val queries = gitHubDatabase.localNotificationsQueries

    fun getAll(): Flow<Notifications> = queries
        .findAll()
        .asFlow()
        .mapToList()
        .map { it.map(NotificationsTable::toNotification) }

    fun upsert(notification: NotificationsTable) {
        queries.insert(notificationsTable = notification)
    }
}
