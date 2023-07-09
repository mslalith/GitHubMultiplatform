package dev.mslalith.githubmultiplatform.data.datasource.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.TrendingRepositoriesTable
import dev.mslalith.githubmultiplatform.data.model.TrendingRepositories
import dev.mslalith.githubmultiplatform.data.model.TrendingRepository
import dev.mslalith.githubmultiplatform.database.GitHubDatabase
import dev.mslalith.githubmultiplatform.domain.dto.toTrendingRepositoryTable
import dev.mslalith.githubmultiplatform.domain.dto.toTrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LocalTrendingRepositoriesDataSource : KoinComponent {

    private val gitHubDatabase by inject<GitHubDatabase>()
    private val queries = gitHubDatabase.localTrendingRepositoriesQueries

    fun getAll(): Flow<TrendingRepositories> = queries
        .findAll()
        .asFlow()
        .mapToList()
        .map { it.map(TrendingRepositoriesTable::toTrendingRepository) }

    fun upsert(repository: TrendingRepository) {
        queries.insert(trendingRepositoriesTable = repository.toTrendingRepositoryTable())
    }
}
