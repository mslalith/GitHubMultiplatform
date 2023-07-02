package dev.mslalith.githubmultiplatform.ui.filters.repository

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.data.model.Selectable
import dev.mslalith.githubmultiplatform.ui.filters.FilterState

class RepositoryTypeFilterState : FilterState<RepositoryTypeFilter, Selectable<RepositoryTypeFilter>>(
    initial = RepositoryTypeFilter.ALL
) {
    override val allTypes = RepositoryTypeFilter.values().toList()

    override fun mapToStringResource(value: RepositoryTypeFilter): StringResource {
        return value.stringResource
    }

    override fun mapToUi(value: RepositoryTypeFilter) = Selectable(
        value = value,
        isSelected = selectedType == value
    )
}
