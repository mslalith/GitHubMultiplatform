package dev.mslalith.githubmultiplatform.ui.screens.issueslist

import androidx.compose.runtime.snapshotFlow
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.Issue
import dev.mslalith.githubmultiplatform.domain.usecase.GetIssuesUseCase
import dev.mslalith.githubmultiplatform.ui.filters.issue.state.IssueStateFilter
import dev.mslalith.githubmultiplatform.ui.filters.issue.state.IssueStateFilterState
import dev.mslalith.githubmultiplatform.ui.filters.issue.visibility.IssueVisibilityFilter
import dev.mslalith.githubmultiplatform.ui.filters.issue.visibility.IssueVisibilityFilterState
import dev.mslalith.githubmultiplatform.ui.screens.issueslist.IssuesListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.issueslist.IssuesListScreenState.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IssuesListScreenModel : StateScreenModel<IssuesListScreenState>(initialState = Loading), KoinComponent {

    private val getIssuesUseCase by inject<GetIssuesUseCase>()

    val issueStateFilterState by inject<IssueStateFilterState>()
    val issueVisibilityFilterState by inject<IssueVisibilityFilterState>()

    private val allFilters = listOf(issueStateFilterState, issueVisibilityFilterState)

    val activeFilterCount: Int
        get() = allFilters.count { !it.isInitial }

    fun clearFilters() = allFilters.forEach { it.reset() }

    private val issues: Flow<List<Issue>> = getIssuesUseCase.run()
        .map { pagedIssues -> pagedIssues.issues.sortedByDescending { it.createdAt } }
        .combine(
            flow = snapshotFlow { issueStateFilterState.selectedType },
            transform = ::filterByIssueState
        )
        .combine(
            flow = snapshotFlow { issueVisibilityFilterState.selectedType },
            transform = ::filterByIssueVisibility
        )

    init {
        issues
            .onStart { mutableState.update { Loading } }
            .onEach { issues -> mutableState.update { Success(issues = issues) } }
            .launchIn(scope = coroutineScope)
    }

    private fun filterByIssueState(issues: List<Issue>, state: IssueStateFilter): List<Issue> = when (state) {
        IssueStateFilter.All -> issues
        IssueStateFilter.Open -> issues.filterNot { it.isClosed }
        IssueStateFilter.Closed -> issues.filter { it.isClosed }
    }

    private fun filterByIssueVisibility(issues: List<Issue>, state: IssueVisibilityFilter): List<Issue> = when (state) {
        IssueVisibilityFilter.All -> issues
        IssueVisibilityFilter.Private -> issues.filter { it.isRepoPrivate }
        IssueVisibilityFilter.Public -> issues.filterNot { it.isRepoPrivate }
    }
}
