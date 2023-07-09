package dev.mslalith.githubmultiplatform.ui.screens.trendingrepositorylist

import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.TrendingRepositories
import dev.mslalith.githubmultiplatform.data.repository.TrendingRepositoriesRepo
import dev.mslalith.githubmultiplatform.ui.state.CommonState
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Loading
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Success
import dev.mslalith.githubmultiplatform.utils.screen.SerializableStateScreenModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.jvm.Transient

internal class TrendingRepositoryListScreenModel : SerializableStateScreenModel<CommonState<TrendingRepositories>>(
    initialState = Loading
) {

    @delegate:Transient
    private val repo by inject<TrendingRepositoriesRepo>()

    fun fetchTrending() {
        coroutineScope.launch {
            mutableState.update { Loading }
            repo.fetch()
            mutableState.update { Success(value = repo.getAll().firstOrNull().orEmpty()) }
        }
    }
}
