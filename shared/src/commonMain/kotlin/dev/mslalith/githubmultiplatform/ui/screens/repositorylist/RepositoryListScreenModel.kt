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
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.jvm.Transient

internal class RepositoryListScreenModel : StateScreenModel<RepositoryListScreenState>(initialState = Loading), KoinComponent, CommonSerializable {

    @delegate:Transient
    private val getRepositoriesUseCase by inject<GetRepositoriesUseCase>()

    @delegate:Transient
    val repositoryTypeFilterState by inject<RepositoryTypeFilterState>()

    private val allFilters = listOf(repositoryTypeFilterState)
    private var repositories: List<Repository> = emptyList()

    init {
        snapshotFlow { repositoryTypeFilterState.selectedType }
            .onEach(::updateReposByRepositoryTypeFilter)
            .launchIn(scope = coroutineScope)
    }

    val activeFilterCount: Int
        get() = allFilters.count { !it.isInitial }

    fun clearFilters() = allFilters.forEach { it.reset() }

    fun fetchRepositories() {
        coroutineScope.launch {
            repositories = getRepositoriesUseCase.run().firstOrNull()?.repositories.orEmpty()
            mutableState.update { Success(repositories = repositories) }
        }
    }

    private fun updateReposByRepositoryTypeFilter(filter: RepositoryTypeFilter) {
        val repos = when (filter) {
            RepositoryTypeFilter.All -> repositories
            RepositoryTypeFilter.Fork -> repositories.filter { it.isFork }
            RepositoryTypeFilter.Private -> repositories.filter { it.isPrivate }
        }
        mutableState.update { Success(repositories = repos) }
    }
}
