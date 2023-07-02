package dev.mslalith.githubmultiplatform.ui.filters

import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.bottomsheets.SelectableListBottomSheetItem

class RepositoryTypeFilterState : FilterState<StringResource, SelectableListBottomSheetItem>(
    initial = SharedRes.strings.all
) {

    override val allTypes = listOf(
        SharedRes.strings.all,
        SharedRes.strings.fork,
        SharedRes.strings.private_
    )

    override fun mapToUi(value: StringResource) = SelectableListBottomSheetItem(
        text = value,
        selected = selectedType == value
    )
}
