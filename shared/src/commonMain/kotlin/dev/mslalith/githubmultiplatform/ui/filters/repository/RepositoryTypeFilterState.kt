package dev.mslalith.githubmultiplatform.ui.filters.repository

import dev.mslalith.githubmultiplatform.data.model.Selectable
import dev.mslalith.githubmultiplatform.ui.filters.base.FilterState
import dev.mslalith.githubmultiplatform.ui.filters.repository.RepositoryTypeFilter.All
import dev.mslalith.githubmultiplatform.ui.filters.repository.RepositoryTypeFilter.Fork
import dev.mslalith.githubmultiplatform.ui.filters.repository.RepositoryTypeFilter.Private

class RepositoryTypeFilterState : FilterState<RepositoryTypeFilter, Selectable<RepositoryTypeFilter>>(
    initial = All
) {
    override val allTypes = listOf(All, Fork, Private)

    override fun mapToUi(value: RepositoryTypeFilter) = Selectable(
        value = value,
        isSelected = selectedType == value
    )
}
