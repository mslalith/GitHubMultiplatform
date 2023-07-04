package dev.mslalith.githubmultiplatform.ui.screens.issueslist

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.Issue
import dev.mslalith.githubmultiplatform.domain.usecase.GetIssuesUseCase
import dev.mslalith.githubmultiplatform.ui.screens.issueslist.IssuesListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.issueslist.IssuesListScreenState.Success
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class IssuesListScreenModel : StateScreenModel<IssuesListScreenState>(initialState = Loading), KoinComponent {

    private val getIssuesUseCase by inject<GetIssuesUseCase>()

    private var issues = emptyList<Issue>()

    fun fetchIssues() {
        coroutineScope.launch {
            issues = getIssuesUseCase.run().firstOrNull()?.issues.orEmpty()
            mutableState.update { Success(issues = issues) }
        }
    }
}
