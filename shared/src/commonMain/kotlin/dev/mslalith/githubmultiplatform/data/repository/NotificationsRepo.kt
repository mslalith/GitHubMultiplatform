package dev.mslalith.githubmultiplatform.data.repository

import dev.mslalith.githubmultiplatform.data.datasource.local.LocalNotificationsDataSource
import dev.mslalith.githubmultiplatform.data.datasource.remote.RemoteNotificationsDataSource
import dev.mslalith.githubmultiplatform.data.model.notification.Notifications
import dev.mslalith.githubmultiplatform.domain.dto.toLocalNotification
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface NotificationsRepo {
    suspend fun fetch()
    fun getAll(): Flow<Notifications>
}
class NotificationsRepoImpl : NotificationsRepo, KoinComponent {

    private val localDataSource by inject<LocalNotificationsDataSource>()
    private val remoteDataSource by inject<RemoteNotificationsDataSource>()

    override suspend fun fetch() {
        try {
            remoteDataSource.fetch().forEach {
                localDataSource.upsert(notification = it.toLocalNotification())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun getAll(): Flow<Notifications> = localDataSource.getAll()
}
