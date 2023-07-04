package dev.mslalith.githubmultiplatform.ui.filters.issue.visibility

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.data.model.Selectable
import dev.mslalith.githubmultiplatform.ui.filters.FilterState

class IssueVisibilityFilterState : FilterState<IssueVisibilityFilter, Selectable<IssueVisibilityFilter>>(
    initial = IssueVisibilityFilter.ALL
) {
    override val allTypes = IssueVisibilityFilter.values().toList()

    override fun mapToStringResource(value: IssueVisibilityFilter): StringResource = value.stringResource
    override fun mapSelectedToStringResource(value: IssueVisibilityFilter): StringResource = when (value) {
        IssueVisibilityFilter.ALL -> SharedRes.strings.visibility
        else -> value.stringResource
    }

    override fun mapToUi(value: IssueVisibilityFilter) = Selectable(
        value = value,
        isSelected = selectedType == value
    )
}
