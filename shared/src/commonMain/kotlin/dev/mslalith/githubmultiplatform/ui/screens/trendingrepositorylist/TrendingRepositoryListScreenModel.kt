package dev.mslalith.githubmultiplatform.ui.screens.trendingrepositorylist

import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.repository.TrendingRepositoriesRepo
import dev.mslalith.githubmultiplatform.ui.screens.trendingrepositorylist.TrendingRepositoryListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.trendingrepositorylist.TrendingRepositoryListScreenState.Success
import dev.mslalith.githubmultiplatform.utils.screen.SerializableStateScreenModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.jvm.Transient

internal class TrendingRepositoryListScreenModel : SerializableStateScreenModel<TrendingRepositoryListScreenState>(initialState = Loading) {

    @delegate:Transient
    private val repo by inject<TrendingRepositoriesRepo>()

    fun fetchTrending() {
        coroutineScope.launch {
            mutableState.update { Loading }
            repo.fetch()
            mutableState.update { Success(repositories = repo.getAll().firstOrNull().orEmpty()) }
        }
    }
}
