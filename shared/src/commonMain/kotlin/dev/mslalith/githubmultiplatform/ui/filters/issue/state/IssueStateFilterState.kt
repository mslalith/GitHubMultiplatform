package dev.mslalith.githubmultiplatform.ui.filters.issue.state

import dev.mslalith.githubmultiplatform.data.model.Selectable
import dev.mslalith.githubmultiplatform.ui.filters.base.FilterState
import dev.mslalith.githubmultiplatform.ui.filters.issue.state.IssueStateFilter.All
import dev.mslalith.githubmultiplatform.ui.filters.issue.state.IssueStateFilter.Closed
import dev.mslalith.githubmultiplatform.ui.filters.issue.state.IssueStateFilter.Open

class IssueStateFilterState : FilterState<IssueStateFilter, Selectable<IssueStateFilter>>(
    initial = Open
) {
    override val allTypes = listOf(Open, Closed, All)

    override fun mapToUi(value: IssueStateFilter) = Selectable(
        value = value,
        isSelected = selectedType == value
    )
}
