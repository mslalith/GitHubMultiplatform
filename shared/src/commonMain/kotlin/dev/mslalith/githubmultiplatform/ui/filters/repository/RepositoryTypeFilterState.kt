package dev.mslalith.githubmultiplatform.ui.filters.repository

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.ui.bottomsheets.SelectableListBottomSheetItem
import dev.mslalith.githubmultiplatform.ui.filters.FilterState

class RepositoryTypeFilterState : FilterState<RepositoryTypeFilter, SelectableListBottomSheetItem<RepositoryTypeFilter>>(
    initial = RepositoryTypeFilter.ALL
) {
    override val allTypes = RepositoryTypeFilter.values().toList()

    override fun mapToStringResource(value: RepositoryTypeFilter): StringResource {
        return value.stringResource
    }

    override fun mapToUi(value: RepositoryTypeFilter) = SelectableListBottomSheetItem(
        value = value,
        text = mapToStringResource(value = value),
        selected = selectedType == value
    )
}
