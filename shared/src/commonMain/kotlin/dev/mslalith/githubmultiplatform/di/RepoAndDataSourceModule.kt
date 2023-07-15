package dev.mslalith.githubmultiplatform.di

import dev.mslalith.githubmultiplatform.data.datasource.local.LocalNotificationsDataSource
import dev.mslalith.githubmultiplatform.data.datasource.local.LocalTrendingRepositoriesDataSource
import dev.mslalith.githubmultiplatform.data.datasource.remote.RemoteNotificationsDataSource
import dev.mslalith.githubmultiplatform.data.datasource.remote.RemoteTrendingRepositoriesDataSource
import dev.mslalith.githubmultiplatform.data.repository.NotificationsRepo
import dev.mslalith.githubmultiplatform.data.repository.NotificationsRepoImpl
import dev.mslalith.githubmultiplatform.data.repository.TrendingRepositoriesRepo
import dev.mslalith.githubmultiplatform.data.repository.TrendingRepositoriesRepoImpl
import org.koin.dsl.module

internal val repoAndDataSourceModule = module {
    single { LocalTrendingRepositoriesDataSource() }
    single { RemoteTrendingRepositoriesDataSource() }
    single<TrendingRepositoriesRepo> { TrendingRepositoriesRepoImpl() }

    single { LocalNotificationsDataSource() }
    single { RemoteNotificationsDataSource() }
    single<NotificationsRepo> { NotificationsRepoImpl() }
}
