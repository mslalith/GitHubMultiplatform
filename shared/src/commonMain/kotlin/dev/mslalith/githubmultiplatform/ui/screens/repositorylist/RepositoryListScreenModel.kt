package dev.mslalith.githubmultiplatform.ui.screens.repositorylist

import androidx.compose.runtime.snapshotFlow
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.Repositories
import dev.mslalith.githubmultiplatform.domain.usecase.GetRepositoriesUseCase
import dev.mslalith.githubmultiplatform.ui.filters.repository.RepositoryTypeFilter
import dev.mslalith.githubmultiplatform.ui.filters.repository.RepositoryTypeFilterState
import dev.mslalith.githubmultiplatform.ui.state.CommonState
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Loading
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Success
import dev.mslalith.githubmultiplatform.utils.screen.SerializableStateScreenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import org.koin.core.component.inject
import kotlin.jvm.Transient

internal class RepositoryListScreenModel : SerializableStateScreenModel<CommonState<Repositories>>(
    initialState = Loading
) {

    @delegate:Transient
    private val getRepositoriesUseCase by inject<GetRepositoriesUseCase>()

    @delegate:Transient
    val repositoryTypeFilterState by inject<RepositoryTypeFilterState>()

    private val allFilters = listOf(repositoryTypeFilterState)

    val activeFilterCount: Int
        get() = allFilters.count { !it.isInitial }

    fun clearFilters() = allFilters.forEach { it.reset() }

    @Transient
    private val repositories: Flow<Repositories> = getRepositoriesUseCase.run()
        .map { pagedIssues -> pagedIssues.repositories.sortedByDescending { it.updatedAt } }
        .combine(
            flow = snapshotFlow { repositoryTypeFilterState.selectedType },
            transform = ::filterByRepositoryType
        )

    init {
        repositories
            .onStart { mutableState.update { Loading } }
            .onEach { issues -> mutableState.update { Success(value = issues) } }
            .launchIn(scope = coroutineScope)
    }

    private fun filterByRepositoryType(repositories: Repositories, filter: RepositoryTypeFilter): Repositories = when (filter) {
        RepositoryTypeFilter.All -> repositories
        RepositoryTypeFilter.Fork -> repositories.filter { it.isFork }
        RepositoryTypeFilter.Private -> repositories.filter { it.isPrivate }
    }
}
