package dev.mslalith.githubmultiplatform.data.datasource.remote

import dev.mslalith.githubmultiplatform.data.model.remote.NotificationRemote
import dev.mslalith.githubmultiplatform.data.network.GitHubClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteNotificationsDataSource : KoinComponent {

    private val gitHubClient by inject<GitHubClient>()

    suspend fun fetch(): List<NotificationRemote> = gitHubClient.getNotifications()
}
