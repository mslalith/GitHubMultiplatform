package dev.mslalith.githubmultiplatform.ui.screens.issueslist

import androidx.compose.runtime.snapshotFlow
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.Issues
import dev.mslalith.githubmultiplatform.data.model.Selectable
import dev.mslalith.githubmultiplatform.domain.usecase.GetIssuesUseCase
import dev.mslalith.githubmultiplatform.ui.filters.base.FilterState
import dev.mslalith.githubmultiplatform.ui.filters.base.FilterUiValue
import dev.mslalith.githubmultiplatform.ui.filters.issue.state.IssueStateFilter
import dev.mslalith.githubmultiplatform.ui.filters.issue.state.IssueStateFilterState
import dev.mslalith.githubmultiplatform.ui.filters.issue.visibility.IssueVisibilityFilter
import dev.mslalith.githubmultiplatform.ui.filters.issue.visibility.IssueVisibilityFilterState
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

class IssuesListScreenModel : SerializableStateScreenModel<CommonState<Issues>>(
    initialState = Loading
) {

    @delegate:Transient
    private val getIssuesUseCase by inject<GetIssuesUseCase>()

    @delegate:Transient
    val issueStateFilterState by inject<IssueStateFilterState>()

    @delegate:Transient
    val issueVisibilityFilterState by inject<IssueVisibilityFilterState>()

    private val allFilters: List<FilterState<out FilterUiValue, out Selectable<out FilterUiValue>>> = listOf(
        issueStateFilterState,
        issueVisibilityFilterState
    )

    val activeFilterCount: Int
        get() = allFilters.count { !it.isInitial }

    fun clearFilters() = allFilters.forEach { it.reset() }

    @Transient
    private val issues: Flow<Issues> = getIssuesUseCase.run()
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
            .onEach { issues -> mutableState.update { Success(value = issues) } }
            .launchIn(scope = coroutineScope)
    }

    private fun filterByIssueState(issues: Issues, state: IssueStateFilter): Issues = when (state) {
        IssueStateFilter.All -> issues
        IssueStateFilter.Open -> issues.filterNot { it.isClosed }
        IssueStateFilter.Closed -> issues.filter { it.isClosed }
    }

    private fun filterByIssueVisibility(issues: Issues, state: IssueVisibilityFilter): Issues = when (state) {
        IssueVisibilityFilter.All -> issues
        IssueVisibilityFilter.Private -> issues.filter { it.isRepoPrivate }
        IssueVisibilityFilter.Public -> issues.filterNot { it.isRepoPrivate }
    }
}
