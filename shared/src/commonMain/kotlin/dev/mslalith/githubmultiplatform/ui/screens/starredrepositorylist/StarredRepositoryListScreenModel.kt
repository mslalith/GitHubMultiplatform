package dev.mslalith.githubmultiplatform.ui.screens.starredrepositorylist

import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.domain.usecase.GetStarredRepositoriesUseCase
import dev.mslalith.githubmultiplatform.ui.screens.starredrepositorylist.StarredRepositoryListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.starredrepositorylist.StarredRepositoryListScreenState.Success
import dev.mslalith.githubmultiplatform.utils.screen.SerializableStateScreenModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.jvm.Transient

internal class StarredRepositoryListScreenModel : SerializableStateScreenModel<StarredRepositoryListScreenState>(initialState = Loading) {

    @delegate:Transient
    private val getStarredRepositoriesUseCase by inject<GetStarredRepositoriesUseCase>()

    fun fetchStarredRepositories() {
        coroutineScope.launch {
            val repositories = getStarredRepositoriesUseCase.run().firstOrNull()?.repositories.orEmpty()
            mutableState.update { Success(repositories = repositories) }
        }
    }
}
