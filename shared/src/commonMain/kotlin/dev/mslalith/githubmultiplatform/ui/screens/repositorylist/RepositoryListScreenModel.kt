package dev.mslalith.githubmultiplatform.ui.screens.repositorylist

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.domain.usecase.GetRepositoriesUseCase
import dev.mslalith.githubmultiplatform.ui.screens.repositorylist.RepositoryListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.repositorylist.RepositoryListScreenState.Success
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class RepositoryListScreenModel : StateScreenModel<RepositoryListScreenState>(initialState = Loading), KoinComponent {

    private val getRepositoriesUseCase by inject<GetRepositoriesUseCase>()

    fun fetchRepositories() {
        coroutineScope.launch {
            val repos = getRepositoriesUseCase.run().firstOrNull()?.repositories.orEmpty()
            mutableState.update { Success(repositories = repos) }
        }
    }
}
