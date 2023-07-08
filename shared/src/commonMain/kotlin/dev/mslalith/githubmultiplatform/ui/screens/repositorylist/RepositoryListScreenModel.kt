package dev.mslalith.githubmultiplatform.ui.screens.repositorylist

import androidx.compose.runtime.snapshotFlow
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.Repository
import dev.mslalith.githubmultiplatform.domain.usecase.GetRepositoriesUseCase
import dev.mslalith.githubmultiplatform.platform.CommonSerializable
import dev.mslalith.githubmultiplatform.ui.filters.repository.RepositoryTypeFilter
import dev.mslalith.githubmultiplatform.ui.filters.repository.RepositoryTypeFilterState
import dev.mslalith.githubmultiplatform.ui.screens.repositorylist.RepositoryListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.repositorylist.RepositoryListScreenState.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.jvm.Transient

internal class RepositoryListScreenModel : StateScreenModel<RepositoryListScreenState>(initialState = Loading), KoinComponent, CommonSerializable {

    @delegate:Transient
    private val getRepositoriesUseCase by inject<GetRepositoriesUseCase>()

    @delegate:Transient
    val repositoryTypeFilterState by inject<RepositoryTypeFilterState>()

    private val allFilters = listOf(repositoryTypeFilterState)

    val activeFilterCount: Int
        get() = allFilters.count { !it.isInitial }

    fun clearFilters() = allFilters.forEach { it.reset() }

    @Transient
    private val repositories: Flow<List<Repository>> = getRepositoriesUseCase.run()
        .map { pagedIssues -> pagedIssues.repositories.sortedByDescending { it.updatedAt } }
        .combine(
            flow = snapshotFlow { repositoryTypeFilterState.selectedType },
            transform = ::filterByRepositoryType
        )

    init {
        repositories
            .onStart { mutableState.update { Loading } }
            .onEach { issues -> mutableState.update { Success(repositories = issues) } }
            .launchIn(scope = coroutineScope)
    }

    private fun filterByRepositoryType(repositories: List<Repository>, filter: RepositoryTypeFilter): List<Repository> = when (filter) {
        RepositoryTypeFilter.All -> repositories
        RepositoryTypeFilter.Fork -> repositories.filter { it.isFork }
        RepositoryTypeFilter.Private -> repositories.filter { it.isPrivate }
    }
}
