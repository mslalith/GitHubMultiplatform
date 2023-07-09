package dev.mslalith.githubmultiplatform.data.repository

import dev.mslalith.githubmultiplatform.data.datasource.local.LocalTrendingRepositoriesDataSource
import dev.mslalith.githubmultiplatform.data.datasource.remote.RemoteTrendingRepositoriesDataSource
import dev.mslalith.githubmultiplatform.data.model.TrendingRepositories
import dev.mslalith.githubmultiplatform.domain.dto.toTrendingRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface TrendingRepositoriesRepo {
    suspend fun fetch()
    fun getAll(): Flow<TrendingRepositories>
}
class TrendingRepositoriesRepoImpl : TrendingRepositoriesRepo, KoinComponent {

    private val localDataSource by inject<LocalTrendingRepositoriesDataSource>()
    private val remoteDataSource by inject<RemoteTrendingRepositoriesDataSource>()

    override suspend fun fetch() {
        try {
            remoteDataSource.fetch().forEach {
                localDataSource.upsert(repository = it.toTrendingRepository())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun getAll(): Flow<TrendingRepositories> = localDataSource.getAll()
}
