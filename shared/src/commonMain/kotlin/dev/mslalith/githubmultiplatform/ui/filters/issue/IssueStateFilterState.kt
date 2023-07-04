package dev.mslalith.githubmultiplatform.ui.filters.issue

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.data.model.Selectable
import dev.mslalith.githubmultiplatform.ui.filters.FilterState

class IssueStateFilterState : FilterState<IssueStateFilter, Selectable<IssueStateFilter>>(
    initial = IssueStateFilter.OPEN
) {
    override val allTypes = IssueStateFilter.values().toList()

    override fun mapToStringResource(value: IssueStateFilter): StringResource = value.stringResource

    override fun mapToUi(value: IssueStateFilter) = Selectable(
        value = value,
        isSelected = selectedType == value
    )
}