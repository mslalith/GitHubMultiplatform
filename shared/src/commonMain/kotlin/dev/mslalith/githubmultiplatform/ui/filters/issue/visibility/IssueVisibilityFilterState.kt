package dev.mslalith.githubmultiplatform.ui.filters.issue.visibility

import dev.mslalith.githubmultiplatform.data.model.Selectable
import dev.mslalith.githubmultiplatform.ui.filters.base.FilterState
import dev.mslalith.githubmultiplatform.ui.filters.issue.visibility.IssueVisibilityFilter.All
import dev.mslalith.githubmultiplatform.ui.filters.issue.visibility.IssueVisibilityFilter.Private
import dev.mslalith.githubmultiplatform.ui.filters.issue.visibility.IssueVisibilityFilter.Public

class IssueVisibilityFilterState : FilterState<IssueVisibilityFilter, Selectable<IssueVisibilityFilter>>(
    initial = All
) {
    override val allTypes = listOf(All, Private, Public)

    override fun mapToUi(value: IssueVisibilityFilter) = Selectable(
        value = value,
        isSelected = selectedType == value
    )
}
