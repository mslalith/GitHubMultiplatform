package dev.mslalith.githubmultiplatform.ui.screens.issueslist

import androidx.compose.runtime.snapshotFlow
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.Issue
import dev.mslalith.githubmultiplatform.domain.usecase.GetIssuesUseCase
import dev.mslalith.githubmultiplatform.ui.filters.issue.state.IssueStateFilter
import dev.mslalith.githubmultiplatform.ui.filters.issue.state.IssueStateFilterState
import dev.mslalith.githubmultiplatform.ui.screens.issueslist.IssuesListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.issueslist.IssuesListScreenState.Success
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IssuesListScreenModel : StateScreenModel<IssuesListScreenState>(initialState = Loading), KoinComponent {

    private val getIssuesUseCase by inject<GetIssuesUseCase>()

    val issueStateFilterState by inject<IssueStateFilterState>()

    private val allFilters = listOf(issueStateFilterState)
    private var issues = emptyList<Issue>()

    val activeFilterCount: Int
        get() = allFilters.count { !it.isInitial }

    fun clearFilters() = allFilters.forEach { it.reset() }

    init {
        snapshotFlow { issueStateFilterState.selectedType }
            .onEach(::updateIssuesByIssueStateFilter)
            .launchIn(scope = coroutineScope)
    }

    fun fetchIssues() {
        coroutineScope.launch {
            issues = getIssuesUseCase.run().firstOrNull()?.issues.orEmpty()
            mutableState.update { Success(issues = issues) }
        }
    }

    private fun updateIssuesByIssueStateFilter(issueStateFilter: IssueStateFilter) {
        val issues = when (issueStateFilter) {
            IssueStateFilter.ALL -> issues
            IssueStateFilter.OPEN -> issues.filter { !it.isClosed }
            IssueStateFilter.CLOSED -> issues.filter { it.isClosed }
        }
        mutableState.update { Success(issues = issues) }
    }
}
